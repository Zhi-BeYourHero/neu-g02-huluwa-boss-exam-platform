package com.boss.bes.exam.service.impl;

import boss.xtrain.cache.redis.service.RedisService;
import com.boss.bes.exam.dao.ExamAnswerRecordDao;
import com.boss.bes.exam.dao.ExamRecordDao;
import com.boss.bes.exam.model.dto.ExamPaperDTO;
import com.boss.bes.exam.model.dto.ExamPaperSubjectDTO;
import com.boss.bes.exam.model.po.ExamAnswerRecord;
import com.boss.bes.exam.service.ComputeAbilityTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 计算能力标签服务接口实现类
 * @create 2020-07-12 16:30
 * @since 1.0
 */
@Service
public class ComputeAbilityTagServiceImpl implements ComputeAbilityTagService {

    @Resource
    private RedisService redisService;

    @Resource
    private ExamRecordDao examRecordDao;

    @Resource
    private ExamAnswerRecordDao answerRecordDao;

    /**
     * 计算能力标签
     *
     * @param paperId 试卷id
     * @param examRecordId 考试记录id
     */
    @Override
    public void computeAbilityTag(Long paperId, Long examRecordId) {
        final String cacheKey = "paper-" + paperId.toString();
        // 获取试卷试题信息
        ExamPaperDTO paperDTO = (ExamPaperDTO)redisService.getCacheObject(cacheKey);
        List<ExamPaperSubjectDTO> subjects = paperDTO.getSubjects();
        // 计算题目类别的难度系数
        Map<String, Double> difficulty = new HashMap<>();
        // 根据题目类别进行分组
        Map<String, List<Long>> categorys = new HashMap<>();
        for (ExamPaperSubjectDTO subject : subjects) {
            String category = subject.getCategory();
            if (categorys.containsKey(category)) {
                categorys.get(category).add(subject.getId());
            } else {
                List<Long> ids = new ArrayList<>();
                ids.add(subject.getId());
                categorys.put(category, ids);
            }
            if (difficulty.containsKey(category)) {
                difficulty.put(category, difficulty.get(category) + subject.getDifficult().doubleValue());
            } else {
                difficulty.put(category, subject.getDifficult().doubleValue());
            }
        }
        for (Map.Entry<String, Double> entry : difficulty.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            difficulty.put(key, value / categorys.get(key).size());
        }
        // 计算各题目类别的做对题目数量
        Map<String, Integer> mount = new HashMap<>();
        // 获取试题答案信息
        List<ExamAnswerRecord> answerRecord = answerRecordDao.getExamAnswerRecordById(examRecordId);
        for (ExamAnswerRecord record : answerRecord) {
            double standard = record.getSubjectScore().doubleValue() * 0.6;
            if (record.getScore().doubleValue() > standard) {
                for (Map.Entry<String, List<Long>> entry : categorys.entrySet()) {
                    String key = entry.getKey();
                    List<Long> value = entry.getValue();
                    if (value.contains(record.getTPId())) {
                        mount.put(key, mount.getOrDefault(key, 0) + 1);
                        break;
                    }
                }
            }
        }
        // 能力标签
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : mount.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if ((double)value / categorys.get(key).size() >= difficulty.get(key)) {
                builder.append(key).append(" ").append("优秀,");
            } else {
                builder.append(key).append(" ").append("一般,");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        // 更新数据库
        examRecordDao.updateTag(examRecordId, builder.toString());
    }
}
