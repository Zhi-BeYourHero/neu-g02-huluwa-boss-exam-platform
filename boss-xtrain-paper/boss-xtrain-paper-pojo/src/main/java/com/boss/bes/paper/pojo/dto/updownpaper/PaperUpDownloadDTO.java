package com.boss.bes.paper.pojo.dto.updownpaper;

import com.boss.bes.paper.pojo.entity.Paper;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import com.boss.bes.paper.pojo.entity.PaperSubjectAnswer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 上传下载DTO
 * @Date: 2020/7/8 8:30
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaperUpDownloadDTO extends Paper {

    /**
     * 公司id
     */
    private  Long companyId;
    /**
     * 题目列表
     */
    private  List<PaperSubject> subjects;
    /**
     * 答案列表
     */
    private  List<PaperSubjectAnswer> answers;


}
