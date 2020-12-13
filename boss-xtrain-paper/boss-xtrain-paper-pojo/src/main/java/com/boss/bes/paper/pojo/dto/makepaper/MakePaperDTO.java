package com.boss.bes.paper.pojo.dto.makepaper;

import com.boss.bes.paper.pojo.entity.Paper;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 组卷DTO
 * @Date: 2020/7/8 8:30
 * @since: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MakePaperDTO extends Paper {

    /**
     * 试卷试题
     * */
    private transient List<SubjectsDTO> subjects;
}
