package com.boss.bes.basedata.pojo.vo.subject;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SubjectIdConditionVO {
    @NotNull
    List<Long> ids;
    public List<Long> getIds() {
        return ids;
    }
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    @Override
    public String toString() {
        return "SubjectIdConditionVO{" +
                "ids=" + ids +
                '}';
    }
}
