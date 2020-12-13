package com.boss.bes.paper.service.util;

import java.io.Serializable;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 9:43
 * @since: 1.0
 */
public class BaseUser  implements Serializable {

    /*用户ID*/
    private Long userId;
    /*机构ID*/
    private Long orgId;
    /* 公司Id*/
    private Long companyId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

