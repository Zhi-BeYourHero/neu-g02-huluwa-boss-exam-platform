package com.boss.bes.exam.service;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 计算能力标签服务接口
 * @create 2020-07-12 16:29
 * @since 1.0
 */
public interface ComputeAbilityTagService {

    /**
     * 计算能力标签
     *
     * @param paperId 试卷id
     * @param examRecordId 考试记录id
     */
    void computeAbilityTag(Long paperId, Long examRecordId);

}
