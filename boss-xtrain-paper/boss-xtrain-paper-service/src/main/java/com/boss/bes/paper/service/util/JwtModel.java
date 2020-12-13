package com.boss.bes.paper.service.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 9:23
 * @since: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {

    private Long userId;

    private Long companyId;

    private Long orgId;

}
