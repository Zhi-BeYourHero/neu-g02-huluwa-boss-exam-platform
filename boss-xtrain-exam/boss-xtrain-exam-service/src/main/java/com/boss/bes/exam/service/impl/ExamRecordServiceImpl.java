package com.boss.bes.exam.service.impl;

import boss.xtrain.cache.redis.service.RedisService;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.dao.ExamAnswerRecordDao;
import com.boss.bes.exam.dao.ExamRecordDao;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamRecordQueryResult;
import com.boss.bes.exam.model.query.ExamRecordQuery;
import com.boss.bes.exam.model.query.PaperQuery;
import com.boss.bes.exam.service.ComputeAbilityTagService;
import com.boss.bes.exam.service.ExamPublishRecordService;
import com.boss.bes.exam.service.ExamRecordService;
import com.boss.bes.exam.service.api.BaseDataFeignService;
import com.boss.bes.exam.service.api.PaperFeignService;
import com.boss.bes.exam.service.api.model.dto.AnswerDTO;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;
import com.boss.bes.exam.service.api.model.vo.SubjectSearchVO;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author fuhaifei
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用来用来查询为评卷提供服务的service
 * @create 2020-07-06 21:00
 * @since 1.0
 */
@Slf4j
@Service
public class ExamRecordServiceImpl implements ExamRecordService{
    private static final String EVALUATE_KEY="exam-evaluate:";
    private static final String SUBJECT_TYPE_KEY = "exam-allSubject";
    private static final String PAPER_KEY = "exam-paper-";
    @Resource
    ExamRecordDao examRecordDao;
    @Resource
    ExamAnswerRecordDao examAnswerRecordDao;
    @Resource
    RedisService redisService;
    @Resource
    ExamPublishRecordService examPublishRecordService;
    @Resource
    PaperFeignService paperFeignService;
    @Resource
    ComputeAbilityTagService computeAbilityTagService;
    /**
     * 基础数据服务调用
     * */
    @Resource
    BaseDataFeignService baseDataFeignService;

    /**
     * 通过条件查询指定考试记录信息
     *
     * @param query 查询对象
     */
    @Override
    public List<ExamRecordQueryResultDTO> getExamRecordByCondition(ExamRecordQuery query) {
        List<ExamRecordQueryResult> examRecordByCondition = examRecordDao.getExamRecordByCondition(query);
        //获取试卷Id和试卷名的map
        Map<Long,String> allPaper = examPublishRecordService.getAllPaperWithoutCompany();
        //遍历转换为DTO
        List<ExamRecordQueryResultDTO> result = new ArrayList<>();
        ExamRecordQueryResultDTO temp;
        for(ExamRecordQueryResult examRecordQueryResult:examRecordByCondition){
            temp = BeanUtil.copyProperties(examRecordQueryResult,ExamRecordQueryResultDTO.class);
            if(temp == null){
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getMessage());
            }
            //通过tpId获取考试记录
            Long tpId = examRecordQueryResult.getTPId();
            temp.setPaperName(allPaper.get(tpId));
            result.add(temp);
        }
        return result;
    }


    /**
     * 传输评卷结果，用于临时存储分题评卷的评卷结果以及暂存评卷结果
     * 缓存按照 examRecord考试记录ID->题目id->评价内容
     * @param evaluateDTOS 传输的试题Id
     */
    @Override
    public void updateExamEvaluation(List<EvaluateDTO> evaluateDTOS) {
        //遍历所有的DTOs
        Map<Long, EvaluateDTO> temp;
        String key;
        for(EvaluateDTO evaluateDTO:evaluateDTOS){
            key = String.format("%s-%d",EVALUATE_KEY,evaluateDTO.getRecordId());
            Object object = redisService.getCacheObject(key);
            if(object == null){
                temp = new HashMap<>();
            }
            else{
                temp = (Map<Long, EvaluateDTO>)object;
            }
            temp.put(evaluateDTO.getSubjectId(),evaluateDTO);
            //将当前评价信息放进Redis缓存
            redisService.setCacheObject(key,temp);
        }
    }

    /**
     * 通过考试记录ID
     *
     * @param id
     */
    @Override
    public Map<Long, EvaluateDTO> getEvaluate(Long id) {
        String key = String.format("%s-%d",EVALUATE_KEY,id);
        Object cacheObject = redisService.getCacheObject(key);
        if(cacheObject == null){
            return null;
        }
        return (Map<Long, EvaluateDTO>) cacheObject;
    }


    /**
     * 按照题型方式进行评卷，获取题型
     *
     * @param subjectTypeEvaluateDTO 考试id+题型查询Dto
     */
    @Override
    public List<PaperSubjectToEvaluateDTO> getPaperSubject(SubjectTypeEvaluateDTO subjectTypeEvaluateDTO) {
        //首先查询属于当前阅卷人的所有考试记录
        ExamRecordQuery examRecordQuery = new ExamRecordQuery();
        examRecordQuery.setReviewerId(subjectTypeEvaluateDTO.getReviewerId());
        //查找未批阅试卷
        examRecordQuery.setStatus(0);
        List<ExamRecordQueryResultDTO> examRecordByCondition = getExamRecordByCondition(examRecordQuery);
        //提取所有试卷ID
        List<Long> paperId = new ArrayList<>();
        for(ExamRecordQueryResultDTO recordQueryResult:examRecordByCondition){
            paperId.add(recordQueryResult.getTPId());
        }
        //如果不存在等待评阅试卷直接返回
        if(paperId.isEmpty()){
            return new ArrayList<>();
        }
        Map<Long, PaperSubjectDTO> allPaperSubjectByType = getAllPaperSubjectByType(paperId, subjectTypeEvaluateDTO.getCategory());
        //查询阅卷记录查看阅卷是否被缓存
        List<PaperSubjectToEvaluateDTO> result = new ArrayList<>();
        //评卷缓存临时变量
        Map<Long, EvaluateDTO> evaluateCache;
        //评卷临时对象
        EvaluateDTO tempEvaluate;
        //临时考试记录列表对象
        List<ExamAnswerRecord> tempExamAnswerRecord;
        //临时试题对象
        PaperSubjectDTO tempPaperSubject;
        //临时存储试卷试题对象
        PaperSubjectToEvaluateDTO tempPaperSubjectToEvaluate;
        for(ExamRecordQueryResultDTO recordQueryResult:examRecordByCondition){
            evaluateCache = getEvaluate(recordQueryResult.getId());
            //获取本张试卷的所有试题回答记录
            tempExamAnswerRecord = examAnswerRecordDao.getExamAnswerRecordById(recordQueryResult.getId());
            //遍历试题回答记录封装评价对象
            for(ExamAnswerRecord examAnswerRecord:tempExamAnswerRecord) {
                //如果评价已经提交直接返回
                if(examAnswerRecord.getStatus() != null && examAnswerRecord.getStatus() != 0){
                    continue;
                }
                tempPaperSubject = allPaperSubjectByType.get(examAnswerRecord.getTPId());
                //设置时间的客观题分数
                if(tempPaperSubject != null){
                    tempPaperSubjectToEvaluate = convertToPaperSubject(tempPaperSubject);
                    //填充题目内容 试题id 试题内容 试题分数 试题难度
                    tempPaperSubjectToEvaluate.setDifficult(tempPaperSubjectToEvaluate.getDifficult());
                    //填充回传的评价信息 我的答案 标准答案
                    tempPaperSubjectToEvaluate.setMyAnswer(examAnswerRecord.getMyAnswer());
                    tempPaperSubjectToEvaluate.setStandardAnswer(examAnswerRecord.getStandardAnswer());
                    //便于更新的 考试记录ID
                    tempPaperSubjectToEvaluate.setRecordId(recordQueryResult.getId());
                    //如果评价缓存不为空填充评价信息
                    if(evaluateCache != null && (tempEvaluate = evaluateCache.get(examAnswerRecord.getTPId())) != null){
                        //设置评价 分数 评价信息
                        tempPaperSubjectToEvaluate.setMyScore(tempEvaluate.getScore());
                        tempPaperSubjectToEvaluate.setEvaluate(tempEvaluate.getEvaluate());
                    }
                    result.add(tempPaperSubjectToEvaluate);
                }
            }
        }
        return result;
    }

    /**
     * 按照提醒方式提交试卷
     *
     * @param evaluateDTOS 评价列表
     */
    @Transactional
    @Override
    public void submitEvaluateByType(List<EvaluateDTO> evaluateDTOS) {
        //首先更新缓存
        updateExamEvaluation(evaluateDTOS);
        //更新数据库
        //首先遍历存储评价状态，修改考试记录答案答案详细表
        int result = examAnswerRecordDao.updateSubjectEvaluate(evaluateDTOS);
        if(evaluateDTOS.size() != result){
            throw new ExamException(ExamExceptionCode.EXAM_EXAM_EVALUATE_ANSWER_RECORD_ERROR.getCode(),
                    ExamExceptionCode.EXAM_EXAM_EVALUATE_ANSWER_RECORD_ERROR.getMessage());
        }
    }

    /**
     * 组合试卷返回评价试卷
     * 贼复杂
     * @param paperQuery 带有考试记录和考试试卷Id的评卷查询对象
     */
    @Override
    public PaperToEvaluateDTO getPaper(PaperQuery paperQuery) {
        //通过考试Id获取考试记录信息
        ExamRecord aimExamRecord = examRecordDao.getExamRecordById(paperQuery.getRecordId());
        //通过考试ID获取所有考试考试详细记录
        Map<Long, ExamAnswerRecordDTO> allExamAnswerRecord = getAllExamAnswerRecord(paperQuery.getRecordId());
        //调用缓存获得缓存的评价记录
        Map<Long,EvaluateDTO> tempEvaluates = getEvaluate(paperQuery.getRecordId());
        //调用考试微服获得试卷详细信息
        PaperToEvaluateDTO paperInformation = getPaperInformation(paperQuery.getPaperId());
        //首先修改试卷基本信息
        paperInformation.setRecordId(paperQuery.getRecordId());
        paperInformation.setScore(aimExamRecord.getObjectiveSubjectScore());
        //遍历试题对象 分别进行客观题和主观题的转化
        List<PaperSubjectToEvaluateDTO> subjects = new ArrayList<>();
        List<PaperSubjectToEvaluateDTO> objects = new ArrayList<>();
        ExamAnswerRecordDTO tempExamAnswer;
        EvaluateDTO tempEvaluate;
        for(PaperSubjectToEvaluateDTO subjectToEvaluateDTO:paperInformation.getSubjectDTOS()){
            tempExamAnswer = allExamAnswerRecord.get(subjectToEvaluateDTO.getId());
            //填充答题信息 标准答案和我的答案
            if(tempExamAnswer == null){
                //无该题目的答题记录
                continue;
            }
            subjectToEvaluateDTO.setMyAnswer(tempExamAnswer.getMyAnswer());
            subjectToEvaluateDTO.setStandardAnswer(tempExamAnswer.getStandardAnswer());
            subjectToEvaluateDTO.setMyScore(tempExamAnswer.getScore());
            subjectToEvaluateDTO.setEvaluate(tempExamAnswer.getEvaluate());
            //查看缓存中是否存在对应阅卷记录
            if(tempEvaluates != null && ((tempEvaluate = tempEvaluates.get(subjectToEvaluateDTO.getId())) != null)){
                //填充评价信息 评价信息 分数
                subjectToEvaluateDTO.setEvaluate(tempEvaluate.getEvaluate());
                subjectToEvaluateDTO.setMyScore(tempEvaluate.getScore());
            }
            subjects.add(subjectToEvaluateDTO);
        }
        for(PaperSubjectToEvaluateDTO objectToEvaluateDTO:paperInformation.getObjectDTOs()){
            tempExamAnswer = allExamAnswerRecord.get(objectToEvaluateDTO.getId());
            //填充答题信息 标准答案和我的答案
            if(tempExamAnswer == null){
                //无该题目的答题记录
                continue;
            }
            objectToEvaluateDTO.setMyAnswer(tempExamAnswer.getMyAnswer());
            objectToEvaluateDTO.setStandardAnswer(tempExamAnswer.getStandardAnswer());
            //设置分数
            objectToEvaluateDTO.setMyScore(tempExamAnswer.getScore());
            //主观题无需评价
            objects.add(objectToEvaluateDTO);
        }
        paperInformation.setObjectDTOs(objects);
        paperInformation.setSubjectDTOS(subjects);
        return paperInformation;
    }


    /**
     * 提交评价结果
     *
     * @param evaluateSubmitDTO 评卷详细列表
     */
    @Transactional
    @Override
    public void submitEvaluation(EvaluateSubmitDTO evaluateSubmitDTO) {
        //获取评价列表
        List<EvaluateDTO> evaluateSubmitDTOS = evaluateSubmitDTO.getEvaluateList();
        //查询考试记录
        ExamRecord examRecordById = examRecordDao.getExamRecordById(evaluateSubmitDTO.getExamRecordId());
        //首先遍历存储评价状态，修改考试记录答案答案详细表
        int result = examAnswerRecordDao.updateSubjectEvaluate(evaluateSubmitDTOS);
        if(result != evaluateSubmitDTOS.size()){
            throw new ExamException(ExamExceptionCode.EXAM_EXAM_EVALUATE_ANSWER_RECORD_ERROR.getCode(),
                    ExamExceptionCode.EXAM_EXAM_EVALUATE_ANSWER_RECORD_ERROR.getMessage());
        }
        //更新考试记录表为已评卷，并更新评卷信息
        examRecordDao.submitPaperEvaluation(evaluateSubmitDTO);
        //TODO 等待测试
        //computeAbilityTagService.computeAbilityTag(evaluateSubmitDTO.getExamRecordId(),examRecordById.getTPId());
        //删除缓存
        redisService.deleteObject(String.format("%s-%d",EVALUATE_KEY,evaluateSubmitDTO.getExamRecordId()));
    }
    /**
     * 通过考试记录ID查询考试记录详细
     *
     * @param examRecordId
     */
    @Override
    public Map<Long,ExamAnswerRecordDTO> getAllExamAnswerRecord(Long examRecordId) {
        List<ExamAnswerRecord> examAnswerRecordById = examAnswerRecordDao.getExamAnswerRecordById(examRecordId);
        Map<Long,ExamAnswerRecordDTO> result = new HashMap<>();
        ExamAnswerRecordDTO temp;
        for(ExamAnswerRecord examAnswerRecord:examAnswerRecordById){
            temp = BeanUtil.copyProperties(examAnswerRecord,ExamAnswerRecordDTO.class);
            if(temp == null){
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getMessage());
            }
            result.put(temp.getTPId(),temp);
        }
        return result;
    }

    /**
     * 更新考试状态为评卷过期
     *
     * @param examId
     */
    @Override
    public int setExamRecordReviewTimeout(Long examId) {
        return examRecordDao.setExamRecordReviewTimeout(examId);
    }

    /**
     * 获取所有主观题题目类型
     */
    @Override
    public List<String> getAllSubjectType() {
        Object cacheObject = redisService.getCacheObject(SUBJECT_TYPE_KEY);
        List<String> result;
        if(cacheObject == null){
            //调用远程服务,并设置缓存为一天
            result = baseDataFeignService.doGetAllSubjectType().getBody();
            if(result == null){
                throw new ExamException(ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode(),
                        ExamExceptionCode.CALL_BASE_DATA_SERVICE_FAILED.getCode());
            }
            redisService.setCacheObject(SUBJECT_TYPE_KEY,result,1, TimeUnit.HOURS);
        }
        else{
            result = (List<String>) cacheObject;
        }
        return result;
    }

    /**
     * 获取所有指定类型题目
     * @param paperIds 目标试卷ID列表
     * @param subjectType 需要的试题类型
     */
    @Override
    public Map<Long, PaperSubjectDTO> getAllPaperSubjectByType(List<Long> paperIds,String subjectType) {
        SubjectSearchVO subjectSearchVO = new SubjectSearchVO();
        subjectSearchVO.setPaperList(paperIds);
        subjectSearchVO.setSubjectType(subjectType);
        CommonRequest<SubjectSearchVO> commonRequest = new CommonRequest<>();
        commonRequest.setBody(subjectSearchVO);
        Map<Long, PaperSubjectDTO> paperSubjectDTOMap = paperFeignService.doGetPapersSubjects(commonRequest).getBody();
        if(paperSubjectDTOMap == null){
            throw new ExamException(ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getCode(),
                    ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getMessage());
        }
        return paperSubjectDTOMap;
    }

    /**
     * 查询并将paper缓存paper
     *
     * @param paperId 查找的PaperId
     */
    @Override
    public PaperToEvaluateDTO getPaperInformation(Long paperId) {
        Object cacheObject = redisService.getCacheObject(PAPER_KEY + paperId);
        PaperContentExamVO examVO;
        if(cacheObject == null){
            CommonRequest<Long> commonRequest = new CommonRequest<>();
            commonRequest.setBody(paperId);
            examVO = paperFeignService.doExamGetPaperInfo(commonRequest).getBody();
            if(examVO == null){
                throw new ExamException(ExamExceptionCode.EXAM_EXAM_GET_PAPER_FAILED.getCode(),
                        ExamExceptionCode.EXAM_EXAM_GET_PAPER_FAILED.getMessage());
            }
            redisService.setCacheObject(PAPER_KEY + paperId,examVO);
        }
        else {
            examVO = (PaperContentExamVO) cacheObject;
        }
        //将examVo转换为试卷对象
        PaperToEvaluateDTO paperToEvaluateDTO = new PaperToEvaluateDTO();
        //转化试卷内容 试卷ID 试卷名称 试卷总分数
        paperToEvaluateDTO.setPaperId(examVO.getId());
        paperToEvaluateDTO.setTitle(examVO.getName());
        paperToEvaluateDTO.setScore(examVO.getScore());
        paperToEvaluateDTO.setDifficulty(examVO.getDifficulty());
        paperToEvaluateDTO.setDiscript(examVO.getDiscript());
        //转化题目内容
        List<PaperSubjectToEvaluateDTO> subjects = new ArrayList<>();
        List<PaperSubjectToEvaluateDTO> objects = new ArrayList<>();
        PaperSubjectToEvaluateDTO temp;
        //转化主观题
        for(PaperSubjectDTO paperSubjectDTO:examVO.getSubjects1()){
            //设置 试题ID 题目 分数
            temp = convertToPaperSubject(paperSubjectDTO);
            subjects.add(temp);
        }
        //转化客观题
        for(PaperSubjectDTO paperSubjectDTO:examVO.getSubjects2()){
            temp = convertToPaperSubject(paperSubjectDTO);
            //设置 试题ID 题目 分数
            objects.add(temp);
        }
        //设置题目列表
        paperToEvaluateDTO.setSubjectDTOS(subjects);
        paperToEvaluateDTO.setObjectDTOs(objects);
        return paperToEvaluateDTO;
    }

    /**
     * 将考试服务试题dto转换为本系统试题DTO
     *
     * @param origin 转化的试题对象
     */
    @Override
    public PaperSubjectToEvaluateDTO convertToPaperSubject(PaperSubjectDTO origin) {
        PaperSubjectToEvaluateDTO temp = new PaperSubjectToEvaluateDTO();
        //设置 试题ID 题目 分数 难度
        temp.setId(origin.getId());
        temp.setSubject(origin.getSubject());
        temp.setScore(origin.getScore());
        temp.setDifficult(origin.getDifficulty().toString());
        temp.setSubjectType(origin.getSubjectType());
        //试题的所有答案
        List<SubjectAnswerDTO> subjectAnswerDTOS = new ArrayList<>();
        for(AnswerDTO answerDTO:origin.getAnswers()){
            subjectAnswerDTOS.add(BeanUtil.copyProperties(answerDTO,SubjectAnswerDTO.class));
        }
        temp.setAnswers(subjectAnswerDTOS);
        return temp;
    }


}
