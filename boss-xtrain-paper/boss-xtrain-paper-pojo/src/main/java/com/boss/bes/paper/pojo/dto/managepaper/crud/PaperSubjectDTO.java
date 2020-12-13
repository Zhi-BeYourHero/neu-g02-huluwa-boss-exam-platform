package com.boss.bes.paper.pojo.dto.managepaper.crud;

import com.boss.bes.paper.pojo.dto.managepaper.answer.AnswerDTO;
import com.boss.bes.paper.pojo.entity.PaperSubject;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @Author: wangziyi
 * @program: boss-xtrain-paper-pojo
 * @Description: 试卷题目数据
 * @Date: 2020/7/5 8:25
 * @since: 1.0
 */
@Data
public class PaperSubjectDTO extends PaperSubject {
    /**
     * 试卷题目答案列表
     * */
    private List<AnswerDTO> answers;

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperSubjectDTO that = (PaperSubjectDTO) o;
        return Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}