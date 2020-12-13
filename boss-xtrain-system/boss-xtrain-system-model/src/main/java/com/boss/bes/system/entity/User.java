package com.boss.bes.system.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;

import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import com.boss.bes.system.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户的实体类 tkMyBatis生成
 * @create 2020-07-07
 * @since 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@JsonInclude()
public class User extends BaseEntity implements Serializable{

    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 部门id
     */
    @Column(name = "t_d_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long departmentId;

    /**
     * 公司id
     */
    @Column(name = "t_c_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 工号
     */
    @Column(name = "code")
    private String code;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 头像
     */
    @Column(name = "profile_picture")
    private String profilePicture;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    @JsonFormat(pattern = "yyyy-MM")
    private Date birthday;

    /**
     * 电话
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 其他联系
     */
    @Column(name = "other")
    private String other;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public User(UserDto userDto){
        this.id = userDto.getId();
        this.departmentId = userDto.getDepartmentId();
        this.companyId = userDto.getCompanyId();
        this.code = userDto.getCode();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.profilePicture = userDto.getProfilePicture();
        this.sex = userDto.getSex();
        this.birthday = userDto.getBirthday();
        this.tel = userDto.getTel();
        this.email = userDto.getEmail();
        this.other = userDto.getOther();
        this.remark = userDto.getRemark();
        this.setCreatedBy(userDto.getCreatedBy());
        this.setCreatedTime(userDto.getCreatedTime());
        this.setUpdatedBy(userDto.getUpdatedBy());
        this.setUpdatedTime(userDto.getUpdatedTime());
        this.setVersion(userDto.getVersion());
    }
}