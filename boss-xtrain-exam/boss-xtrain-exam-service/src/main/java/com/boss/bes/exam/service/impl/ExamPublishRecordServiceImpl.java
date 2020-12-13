package com.boss.bes.exam.service.impl;
import boss.xtrain.cache.redis.service.RedisService;
import boss.xtrain.core.data.convention.common.CommonRequest;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.util.cdn.CdnUtil;
import boss.xtrain.util.file.FileUtil;
import boss.xtrain.util.snowflake.SnowflakeWorker;
import com.boss.bes.exam.dao.ExamPublishRecordDao;
import com.boss.bes.exam.model.dto.*;
import com.boss.bes.exam.model.query.ExamPublishRecordQuery;
import com.boss.bes.exam.model.po.ExamPublishRecord;
import com.boss.bes.exam.model.query.ExamPublishRecordQueryWithId;
import com.boss.bes.exam.service.*;
import boss.xtrain.util.convert.BeanUtil;
import com.boss.bes.exam.service.api.PaperFeignService;
import com.boss.bes.exam.service.api.SystemFeignService;
import com.boss.bes.exam.service.api.model.dto.PaperCompanyListDTO;
import com.boss.bes.exam.util.ExamException;
import com.boss.bes.exam.util.ExamExceptionCode;
import com.boss.bes.exam.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ExamPublishRecordServiceImpl implements ExamPublishRecordService{
    private static final String REVIEWER_KEY = "exam-allReview";
    private static final String PAPER_KEY = "exam-allPaper";
    private static final String EXAM_URL = "http://www.xxdmx.top/exam/login?id=";
    /**
     * 缓存服务调用
     * */
    @Resource
    RedisService redisService;
    /**
     * 考试发布Dao
     * */
    @Resource
    ExamPublishRecordDao  examPublishRecordDao;
    /**
     * 系统服务调用
     * */
    @Resource
    SystemFeignService systemFeignService;
    /**
     * 考试服务调用
     * */
    @Resource
    PaperFeignService paperFeignService;

    /**
     * rabbitMq服务调用
     * */
    @Resource
    RabbitQueueService rabbitQueueService;
    /**
     * 获取考试发布结果
     * */
    @Override
    public List<ExamPublishRecordResultDTO> getPublishRecord(ExamPublishRecordQuery queryDTO) {
        ExamPublishRecordQueryWithId queryWithId;
        //首先复制当前DTO属性到带ID DTO属性
        queryWithId = BeanUtil.copyProperties(queryDTO, ExamPublishRecordQueryWithId.class);
        //若复制结果为空排除复制异常
        if(queryWithId == null)
            throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getCode(),
                    ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getCode());
        //首先调用用户ID微服务，获取用户信息
        //所有符合条件的发布者,如果未传入直接返回所有
        String publishUser = queryWithId.getPublishUser();
        if(publishUser == null)
            publishUser = "";
        //调用feign接口查询
        Map<Long, String> allPublisher;
        allPublisher = systemFeignService.getAllPublisher(publishUser);

        //在查询用户名称不为空的情况下才添加ID列表
        if(queryWithId.getPublishUser() != null){
            Set<Long> keys = allPublisher.keySet();
            List<Long> ids = new ArrayList<>(keys);
            queryWithId.setUserIds(ids);
        }
        //如果ID为空,直接返回空结果
        List<ExamPublishRecordResultDTO> result =  new ArrayList<>();
        if(queryWithId.getUserIds() != null && queryWithId.getUserIds().isEmpty())
            return result;
        //调用数据库接口进行查询
        List<ExamPublishRecord> examPublishRecords = examPublishRecordDao.queryByCondition(queryWithId);
        //util将entity转换为DTO
        //获取阅卷人
        Map<Long, String> allReviewer = getAllReviewer();
        //通过考试Id获取所有考试试卷
        Map<Long, String> allPaper = getAllPaper(queryDTO.getCompanyId());
        for(ExamPublishRecord examPublishRecord:examPublishRecords) {
            ExamPublishRecordResultDTO examPublishRecordResultDTO =
                    BeanUtil.copyProperties(examPublishRecord, ExamPublishRecordResultDTO.class);
            //如果复制为空,抛出copy异常
            if (examPublishRecordResultDTO == null)
                throw new ExamException(ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getCode(),
                        ExamExceptionCode.EXAM_CONVERT_ENTITY_TO_DTO_FAILED.getMessage());
            //设置发布人名称
            examPublishRecordResultDTO.setPublisherName(allPublisher.get(examPublishRecord.getPublisher()));
            //初始化阅卷人Map
            Map<Long, String> reviewers = new HashMap<>();
            //首先调用Dao获取当前考试对应阅卷人ID列表
            List<Long> reviewerIds = examPublishRecordDao.queryAllReviewer(examPublishRecordResultDTO.getId());
            //将阅卷人信息存储到DTO中
            if (!reviewerIds.isEmpty()) {
                for (Long reviewerId : reviewerIds) {
                    reviewers.put(reviewerId, allReviewer.get(reviewerId));
                }
                examPublishRecordResultDTO.setReviewers(reviewers);
            }
            //将试卷名称存储到DTO中
            examPublishRecordResultDTO.setPaperName(allPaper.get(examPublishRecord.getTPId()));
            result.add(examPublishRecordResultDTO);
        }
        return result;
    }

    /**
     * 删除考试发布记录相关信息
     *
     * @param  examPublishRecordDeleteDTOS 删除的考试记录Id列表
     */
    @Override
    @Transactional
    public int deletePublishRecord(List<ExamPublishRecordDeleteDTO> examPublishRecordDeleteDTOS) {
        //调用DAO接口进行删除
        ExamPublishRecord tempExamPublishRecord;
        int deleteNumber;
        for(ExamPublishRecordDeleteDTO deleteDTO:examPublishRecordDeleteDTOS){
            tempExamPublishRecord = examPublishRecordDao.getExamPublishRecordById(deleteDTO.getId());
            if(tempExamPublishRecord == null || tempExamPublishRecord.getStatus() != 0 ||
                    !tempExamPublishRecord.getVersion().equals(deleteDTO.getVersion())){
                throw new ExamException(ExamExceptionCode.EXAM_PUBLISHED_DELETE.getCode(),
                        ExamExceptionCode.EXAM_PUBLISHED_DELETE.getMessage());
            }
            //首先删除阅卷关联表
            examPublishRecordDao.deleteReviewerRelation(deleteDTO.getId());
            //首先删除考试记录表 再次进行version和status比较
            deleteNumber = examPublishRecordDao.deletePublishRecord(deleteDTO);
            if(deleteNumber != 1){
                log.info("删除数字{}",deleteNumber);
                throw new ExamException(ExamExceptionCode.EXAM_PUBLISHED_DELETE_FAILED.getCode(),
                        ExamExceptionCode.EXAM_PUBLISHED_DELETE_FAILED.getMessage());
            }
        }
        //表示删除成功
        return 1;
    }

    /**
     * 发布已经插入考试
     * @param publishExamDTOS 发布考试的参数DTO
     */
    @Transactional
    @Override
    public int publishExam(List<PublishExamDTO> publishExamDTOS) {
        //遍历DTO进行发布操作
        ExamPublishRecord tempExamPublishRecord;
        for(PublishExamDTO publishExamDTO:publishExamDTOS){
            //首先调用DAO查询状态和version
            tempExamPublishRecord = examPublishRecordDao.getExamPublishRecordById(publishExamDTO.getId());
            if(tempExamPublishRecord == null || tempExamPublishRecord.getStatus() != 0 ||
                    !tempExamPublishRecord.getVersion().equals(publishExamDTO.getVersion())){
                throw new ExamException(ExamExceptionCode.EXAM_PUBLISH_FAILED.getCode(),
                        ExamExceptionCode.EXAM_PUBLISH_FAILED.getMessage());
            }
            //TODO 需要用到分布式事务
            CommonRequest<Long> commonRequest = new CommonRequest<>();
            commonRequest.setBody(tempExamPublishRecord.getTPId());
            //更新失败则回滚
            CommonResponse<Boolean> booleanCommonResponse = paperFeignService.doExamPublishTimesAdd(commonRequest);
            if(booleanCommonResponse == null || !booleanCommonResponse.getBody()){
                throw new ExamException(ExamExceptionCode.EXAM_EXAM_PAPER_PUBLISH_FAILED.getCode(),
                        ExamExceptionCode.EXAM_EXAM_PAPER_PUBLISH_FAILED.getMessage());
            }
            //生成qrcode到classpath下
            File file = QRCodeUtil.qrCodeCreate(String.format("%s%d", EXAM_URL, publishExamDTO.getId()),
                    "/tmp", null,
                    String.format("qr-%d",tempExamPublishRecord.getId()), false);
            if(file == null)
                throw new ExamException(ExamExceptionCode.EXAM_QR_CODE_GENERATE_FAILED.getCode(),
                        ExamExceptionCode.EXAM_QR_CODE_GENERATE_FAILED.getMessage());
            //调用工具类上传获得url
            if(!CdnUtil.upload(file, "qrcode/" + file.getName())){
                throw new ExamException(ExamExceptionCode.EXAM_UPLOAD_IMAGE.getCode(),
                        ExamExceptionCode.EXAM_UPLOAD_IMAGE.getMessage());
            }
            //删除文件
            FileUtil.deleteFile(file.getAbsolutePath());
            //设置qrCodeUrl
            tempExamPublishRecord.setQrcodeUrl(CdnUtil.getUrl("qrcode/"+file.getName()));

            //进行数据库更新
            if(examPublishRecordDao.publishExam(tempExamPublishRecord) != 1)
                throw new ExamException(ExamExceptionCode.EXAM_PUBLISH_EXAM_FAILED.getCode(),
                        ExamExceptionCode.EXAM_PUBLISH_EXAM_FAILED.getMessage());

            //添加阅卷过期消息
            RabbitMessageDTO<Long> rabbitMessageDTO = new RabbitMessageDTO<>();
            //标识为考试超时消息
            rabbitMessageDTO.setMessageType(1);
            rabbitMessageDTO.setData(publishExamDTO.getId());
            //设置超时时间为阅卷停止时间当前时间的差值
            rabbitMessageDTO.setDelayTime(
                    (int)Duration.between(LocalDateTime.now(),tempExamPublishRecord.getMarkingStopTime()).toMillis());
            //向消息队列中添加阅卷过期数据
            rabbitQueueService.sendMessage(rabbitMessageDTO);

            //添加开始阅卷消息
            rabbitMessageDTO.setMessageType(2);
            rabbitMessageDTO.setData(tempExamPublishRecord.getId());
            //设置超时时间为考试停止时间当前时间的差值
            rabbitMessageDTO.setDelayTime(
                    (int)Duration.between(LocalDateTime.now(),tempExamPublishRecord.getEndTime()).toMillis());
            //向消息队列中添加考试停止数据
            rabbitQueueService.sendMessage(rabbitMessageDTO);
        }
        return 1;
    }

    /**
     * 插入考试
     * 由于涉及到了多个表的插入通过事务控制
     * @param examAddDTO 增加考试Dto
     */
    @Transactional
    @Override
    public int addExam(ExamAddDTO examAddDTO) {
        //将DTO转换为Entity
        ExamPublishRecord examPublishRecord = BeanUtil.copyProperties(examAddDTO, ExamPublishRecord.class);
        //如果转化结果为空抛出转化失败异常
        if(examPublishRecord == null)
            throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getCode()
                    ,ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getMessage());
        //调用雪花算法生成考试ID
        examPublishRecord.setId(new SnowflakeWorker(0L,0L,0L).nextId());
        //生成场次号码,根据考试名称和考试考试ID后八位生成
        examPublishRecord.setExamBatchNo(String.format("%s-%5d",
                "bs",examPublishRecord.getId() % 100000));
        //设置考试创建人
        examPublishRecord.setCreatedBy(examPublishRecord.getPublisher());
        //设置默认发布状态为未发布
        examPublishRecord.setStatus(0);
        //设置版本号为0
        examPublishRecord.setVersion(0L);
        //将考试记录插入到考试记录表
        int result = examPublishRecordDao.insertExam(examPublishRecord);
        if(result != 1)
            throw new ExamException(ExamExceptionCode.EXAM_INSERT_FAILED.getCode(),
                    ExamExceptionCode.EXAM_INSERT_FAILED.getMessage());
        //将试卷和评卷人关系 转换为DTO,并插入到表中
        insertRelation(examAddDTO.getReviewerId(),examPublishRecord.getId());
        //调用发布接口,发布信息
        if(examAddDTO.getStatus() == 1){
            List<PublishExamDTO> publishExamDTOS = new ArrayList<>();
            PublishExamDTO publishExamDTO = new PublishExamDTO();
            publishExamDTO.setId(examPublishRecord.getId());
            publishExamDTO.setVersion(0L);
            publishExamDTOS.add(publishExamDTO);
            publishExam(publishExamDTOS);
        }
        return 1;
    }

    /**
     * 更新考试记录
     * 通过传入数据更新考试记录
     * @param examUpdateDTO 更新考试Dto
     */
    @Transactional
    @Override
    public int updateExam(ExamUpdateDTO examUpdateDTO) {
        //首先查询数据库记录
        ExamPublishRecord examPublishRecord = examPublishRecordDao.getExamPublishRecordById(examUpdateDTO.getId());
        if(examPublishRecord == null || examPublishRecord.getStatus() != 0||
                !examPublishRecord.getVersion().equals(examUpdateDTO.getVersion())){
            throw new ExamException(ExamExceptionCode.EXAM_UPDATE_LATE.getCode(),
                    ExamExceptionCode.EXAM_UPDATE_LATE.getMessage());
        }
        //删除关联表中的阅卷关系
        examPublishRecordDao.deleteReviewerRelation(examPublishRecord.getId());
        //新插入阅卷关系
        ExamPublishRecord updateRecord = BeanUtil.copyProperties(examUpdateDTO,ExamPublishRecord.class);
        if(updateRecord == null){
            throw new ExamException(ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getCode()
                    ,ExamExceptionCode.EXAM_CONVERT_DTO_TO_ENTITY_FAILED.getMessage());
        }
        //新插入阅卷关系表
        //将试卷和评卷人关系 转换为DTO,并插入到表中
        insertRelation(examUpdateDTO.getReviewerId(),examPublishRecord.getId());
        //调用dao接口更新考试
        examPublishRecordDao.updateExam(updateRecord);
        //如果设置状态为发布,调用发布service进行发布
        //调用发布接口,发布信息
        if(examUpdateDTO.getStatus() == 1){
            List<PublishExamDTO> publishExamDTOS = new ArrayList<>();
            PublishExamDTO publishExamDTO = new PublishExamDTO();
            publishExamDTO.setId(examPublishRecord.getId());
            publishExamDTO.setVersion(examPublishRecord.getVersion()+1);
            publishExamDTOS.add(publishExamDTO);
            publishExam(publishExamDTOS);
        }
        return 1;
    }

    @Override
    @Transactional
    public void insertRelation(List<Long> reviewerIds,Long id){
        List<ExamReviewerRelationDTO> relations = new ArrayList<>();
        for(Long reviewerId:reviewerIds){
            relations.add(new ExamReviewerRelationDTO(id,reviewerId));
        }
        //将阅卷关系插入到阅卷关系表
        int number = examPublishRecordDao.insertReviewerRelation(relations);
        //如果未完全插入,抛出异常回滚事务
        if(number != relations.size())
            throw new ExamException(ExamExceptionCode.EXAM_PUBLISH_REVIEWER_RELATION_GENERATE_FAILED.getCode(),
                    ExamExceptionCode.EXAM_PUBLISH_REVIEWER_RELATION_GENERATE_FAILED.getMessage());
    }

    /**
     * 获取所有阅卷人信息
     */
    @Override
    public Map<Long, String> getAllReviewer() {
        Map<Long,String> result;
        Object cacheObject = redisService.getCacheObject(REVIEWER_KEY);
        if(cacheObject == null){
            result = systemFeignService.getAllReviewer();
            //设置缓存时间为1天
            redisService.setCacheObject(REVIEWER_KEY,result,1, TimeUnit.HOURS);
        }
        else {
            result = (Map<Long,String>)cacheObject;
        }
        return result;
    }

    /**
     * 获取所有试卷信息
     */
    @Override
    public Map<Long, String> getAllPaper(Long companyId) {
        Object cacheObject = redisService.getCacheObject(PAPER_KEY);
        Map<Long, PaperCompanyListDTO> temp;
        Map<Long, String> result = new HashMap<>();
        if(cacheObject == null){
            //调用远程服务,并设置缓存为一天
            Map<Long, PaperCompanyListDTO> body = paperFeignService.doGetPaperAllIds().getBody();
            if(body == null){
                throw new ExamException(ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getCode(),
                        ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getMessage());
            }
            temp = body;
            redisService.setCacheObject(PAPER_KEY,temp,1,TimeUnit.HOURS);
        }
        else{
            log.info("使用缓存");
            temp = (Map<Long, PaperCompanyListDTO>) cacheObject;
        }
        //遍历生成结果
        for (Map.Entry<Long, PaperCompanyListDTO> entry : temp.entrySet()) {
            if (entry.getValue().getCompanyId().equals(companyId)) {
                result.put(entry.getKey(), entry.getValue().getName());
            }
        }
        return result;
    }

    /**
     * 获取所有试卷，不通过公司Id
     */
    @Override
    public Map<Long, String> getAllPaperWithoutCompany() {
        Object cacheObject = redisService.getCacheObject(PAPER_KEY);
        Map<Long, PaperCompanyListDTO> temp;
        Map<Long, String> result = new HashMap<>();
        if(cacheObject == null){
            //调用远程服务,并设置缓存为一天
            Map<Long, PaperCompanyListDTO> body = paperFeignService.doGetPaperAllIds().getBody();
            if(body == null){
                throw new ExamException(ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getCode(),
                        ExamExceptionCode.CALL_PAPER_SERVICE_FAILED.getMessage());
            }
            temp = body;
            redisService.setCacheObject(PAPER_KEY,temp,1,TimeUnit.HOURS);
        }
        else{
            log.info("使用缓存");
            temp = (Map<Long, PaperCompanyListDTO>) cacheObject;
        }
        //遍历生成结果
        for (Map.Entry<Long, PaperCompanyListDTO> entry : temp.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getName());
        }
        return result;
    }


    /**
     * 更新考试记录为停止阅卷
     *
     * @param id 发布考试Id
     */
    @Override
    public int setReviewTimeOut(Long id) {
        return examPublishRecordDao.setReviewTimeOut(id);
    }

    /**
     * 通过id获取考试发布记录
     *
     * @param id id
     * @return 考试发布记录
     */
    @Override
    public ExamPublishRecordResultDTO getById(Long id) {
        ExamPublishRecord publishRecord = examPublishRecordDao.getById(id);
        return BeanUtil.copyProperties(publishRecord, ExamPublishRecordResultDTO.class);
    }

    /**
     * 通过id获取考试试卷id
     *
     * @param id id
     * @return 考试试卷id
     */
    @Override
    public ExamInfoDTO getPaperIdById(Long id) {
        return BeanUtil.copyProperties(examPublishRecordDao.getPaperIdById(id), ExamInfoDTO.class);
    }

    /**
     * 测试试卷发布次数加一方法
     * fhf
     *
     * @param id 试卷ID
     */
    @Override
    public Boolean publishPaper(Long id) {
        CommonRequest<Long> commonRequest = new CommonRequest<>();
        commonRequest.setBody(id);
        return paperFeignService.doExamPublishTimesAdd(commonRequest).getBody();
    }


}

