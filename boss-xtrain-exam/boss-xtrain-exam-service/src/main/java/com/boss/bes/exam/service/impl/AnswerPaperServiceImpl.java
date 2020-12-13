package com.boss.bes.exam.service.impl;

import boss.xtrain.cache.redis.lock.Lock;
import boss.xtrain.cache.redis.service.RedisService;
import boss.xtrain.core.data.convention.common.CommonPage;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.util.convert.BeanException;
import boss.xtrain.util.convert.BeanUtil;
import boss.xtrain.util.id.SnowFlakeUtil;
import com.boss.bes.exam.dao.ExamAnswerRecordDao;
import com.boss.bes.exam.dao.ExamPublishRecordDao;
import com.boss.bes.exam.dao.ExamRecordDao;
import com.boss.bes.exam.dao.ExamUserDao;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.po.ExamRecord;
import com.boss.bes.exam.model.po.ExamUser;
import com.boss.bes.exam.model.query.AnswerPaperQuery;
import com.boss.bes.exam.service.AnswerPaperService;
import com.boss.bes.exam.service.api.BaseDataFeignService;
import com.boss.bes.exam.service.api.PaperFeignService;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.service.api.model.dto.AnswerDTO;
import com.boss.bes.exam.service.api.model.dto.PaperSubjectDTO;
import com.boss.bes.exam.service.api.model.vo.PaperContentExamVO;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 答卷查询服务实现类
 * @create 2020-07-10 17:56
 * @since 1.0
 */
@Service
public class AnswerPaperServiceImpl implements AnswerPaperService {

    @Resource
    private ExamPublishRecordDao examPublishRecordDao;

    @Resource
    private ExamAnswerRecordDao examAnswerRecordDao;

    @Resource
    private ExamRecordDao examRecordDao;

    @Resource
    private ExamUserDao examUserDao;

    @Resource
    private RedisService redisService;

    @Resource
    private Lock lock;

    @Resource
    private SystemFeignService systemFeignService;

    @Resource
    private PaperFeignService paperFeignService;

    @Resource
    private BaseDataFeignService baseDataFeignService;
    /**
     * 答卷查询
     *
     * @param query 查询对象
     * @return 查询结果
     */
    @Override
    public CommonPage<AnswerPaperDTO> answerQuery(AnswerPaperQuery query) {
        CommonPage<AnswerPaperDTO> commonPage = new CommonPage<>();
        try {
            // 获取所有符合条件的发布考试记录
            ExamPublishRecord publishRecord = BeanUtil.copyProperties(query, ExamPublishRecord.class);
            if (query.getPublisher() != null) {
                Map<Long, String> publishers = systemFeignService.getAllPublisher("");
                for (Map.Entry<Long, String> entry : publishers.entrySet()) {
                    if (entry.getValue().equals(query.getPublisher())) {
                        publishRecord.setPublisher(entry.getKey());
                        break;
                    }
                }
            }
            List<ExamPublishRecord> publishRecords = examPublishRecordDao.getId(publishRecord);
            // 如果没有符合条件的发布考试记录，直接返回
            if (publishRecords.isEmpty()) {
                commonPage.setRows(new ArrayList<>());
                return commonPage;
            }
            // 获取所有的发布考试记录id
            List<Long> ids = new ArrayList<>();
            for (ExamPublishRecord record : publishRecords) {
                ids.add(record.getId());
            }
            // 分页查询
            String orderBy = query.getOrderByColumn() + " " + query.getIsAsc();
            startPage(query.getPageIndex(), query.getPageSize(), orderBy);
            PageInfo<ExamRecord> pageInfo = new PageInfo<>(examRecordDao.getById(ids));
            List<ExamRecord> examRecords = pageInfo.getList();
            List<AnswerPaperDTO> result = new ArrayList<>();
            // 获取对应考试人员的手机号与姓名
            for (ExamRecord record : examRecords) {
                AnswerPaperDTO answerPaperDTO = BeanUtil.copyProperties(record, AnswerPaperDTO.class);
                ExamPublishRecord examPublishRecord = examPublishRecordDao.getById(record.getTPId());
                BeanUtil.copyBeanProp(answerPaperDTO, examPublishRecord);
                ExamUser examUser = examUserDao.selectById(record.getTUId());
                answerPaperDTO.setEndTime(examPublishRecord.getEndTime());
                answerPaperDTO.setMobile(examUser.getMobile());
                answerPaperDTO.setName(examUser.getName());
                result.add(answerPaperDTO);
            }
            // 设置分页信息
            commonPage.setPageIndex(pageInfo.getPageNum());
            commonPage.setPageSize(pageInfo.getSize());
            commonPage.setPages(pageInfo.getPages());
            commonPage.setTotal(pageInfo.getTotal());
            commonPage.setRows(result);
        } catch (BeanException e) {
            throw new ExamException(ExamExceptionCode.ANSWER_PAPER_ERROR);
        }
        return commonPage;
    }

    /**
     * 提交答卷
     *
     * @param paperAnswerDTO 试卷信息
     * @return 是否提交成功
     */
    @Override
    public boolean submitPaper(ExamPaperAnswerDTO paperAnswerDTO) {
        // 客观题
        List<String> list = new ArrayList<>();
        list.add("单选题");
        list.add("多选题");
        list.add("判断题");
        // 阅卷人缓存的键
        final String EXAMINER = "examiner";
        // 发布考试记录
        ExamPublishRecord publishRecord = examPublishRecordDao.getById(paperAnswerDTO.getRid());
        // 考试记录id
        String examId = SnowFlakeUtil.getId();
        // 考试人员id
        Long id = paperAnswerDTO.getId();
        // 客观题分数
        double score = 0;
        try {
            for (ExamSubjectDTO subject : paperAnswerDTO.getSubjects()) {
                // 答案明细id
                String answerId = SnowFlakeUtil.getId();
                ExamAnswerRecord answerRecord = new ExamAnswerRecord();
                answerRecord.setId(Long.valueOf(answerId));
                answerRecord.setTPId(subject.getId());
                answerRecord.setTRId(Long.valueOf(examId));
                answerRecord.setMyAnswer(subject.getMyAnswer());
                answerRecord.setStandardAnswer(subject.getStandardAnswer());
                answerRecord.setSubjectScore(subject.getSubjectScore());
                if (list.contains(subject.getType()) &&
                        subject.getMyAnswer().equals(subject.getStandardAnswer())) {
                    answerRecord.setScore(subject.getSubjectScore());
                    score += subject.getSubjectScore().doubleValue();
                }
                answerRecord.setVersion(1L);
                examAnswerRecordDao.insert(answerRecord);
            }
            // 考试记录
            ExamRecord examRecord = new ExamRecord();
            examRecord.setId(Long.valueOf(examId));
            examRecord.setTUId(id);
            examRecord.setTPId(publishRecord.getId());
            examRecord.setPlanStartTime(publishRecord.getStartTime());
            examRecord.setPlanEndTime(publishRecord.getEndTime());
            examRecord.setActualStartTime(paperAnswerDTO.getStartTime());
            examRecord.setActualEndTime(paperAnswerDTO.getEndTime());
            examRecord.setObjectiveSubjectScore(BigDecimal.valueOf(score));
            examRecord.setScore(BigDecimal.valueOf(score));
            examRecord.setVersion(0L);
            examRecord.setStatus(0);
            // 阅卷人id
            List<Long> examinerId = examPublishRecordDao.queryAllReviewer(publishRecord.getId());
            Random random = new Random();
            examRecord.setTYId(examinerId.get(random.nextInt(examinerId.size())));
            return examRecordDao.insert(examRecord);
        } catch (NumberFormatException e) {
            throw new ExamException(ExamExceptionCode.SUBMIT_ERROR);
        }
    }

    /**
     * 获取试卷信息
     *
     * @param paperId 试卷id
     * @return 试卷信息
     */
    @Override
    public List<ExamPaperSubjectDTO> paperInfo(Long paperId) {
        // 试卷缓存的键
        final String key = "exam-paper-" + paperId.toString();
        CommonRequest<Long> request = new CommonRequest<>();
        request.setBody(paperId);
        // 从缓存中获取试卷信息
        PaperContentExamVO paperInfo = (PaperContentExamVO)redisService.getCacheObject(key);
        if (paperInfo == null) {
            paperInfo = paperFeignService.doExamGetPaperInfo(request).getBody();
            redisService.setCacheObject(key, paperInfo);
        }
        // 试卷信息列表
        List<ExamPaperSubjectDTO> list = new ArrayList<>();
        // 获取客观题信息
        for (PaperSubjectDTO dto : paperInfo.getSubjects2()) {
            list.add(convert(dto));
        }
        // 获取主观题信息
        for (PaperSubjectDTO dto : paperInfo.getSubjects1()) {
            list.add(convert(dto));
        }
        return list;
    }

    private ExamPaperSubjectDTO convert(PaperSubjectDTO dto) {
        ExamPaperSubjectDTO paperDTO = BeanUtil.copyProperties(dto, ExamPaperSubjectDTO.class);
        // 题目类别信息
        CommonRequest<Long> request = new CommonRequest<>();
        request.setBody(dto.getCategoryId());
        CommonResponse<List<String>> categoryName = baseDataFeignService.doGetCategoryName(request);
        if (!categoryName.getBody().isEmpty()) {
            paperDTO.setCategory(categoryName.getBody().get(0));
        }
        // 题目难度
        paperDTO.setDifficult(BigDecimal.valueOf((double)dto.getDifficulty() / 10));
        // 设置参数选项
        List<String> options = new ArrayList<>();
        for (AnswerDTO answer : dto.getAnswers()) {
            if (Boolean.TRUE.equals(answer.getRightAnswer())) {
                paperDTO.setAnswer(answer.getAnswer());
            }
            options.add(answer.getAnswer());
        }
        paperDTO.setOptions(options);
        return paperDTO;
    }
}
