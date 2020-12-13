package com.boss.bes.basedata.pojo.vo.subjecttype;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectTypeListVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "SubjectTypeListVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
