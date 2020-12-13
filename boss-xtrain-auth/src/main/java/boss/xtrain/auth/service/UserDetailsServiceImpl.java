package boss.xtrain.auth.service;

import boss.xtrain.auth.api.RemoteUserService;
import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.enums.UserStatus;
import boss.xtrain.core.exception.BaseException;
import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.domain.SysUser;
import boss.xtrain.security.model.UserInfo;
import boss.xtrain.security.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 用户信息处理
 * @create 2020-07-06
 * @since
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        CommonResponse<UserInfo> userResult = remoteUserService.getUserInfo(username);
        checkUser(userResult, username);
        return getUserDetails(userResult);
    }

    public void checkUser(CommonResponse<UserInfo> userResult, String username)
    {
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getBody()))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(userResult.getBody().getSysUser().getStatus()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(userResult.getBody().getSysUser().getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
    }

    private UserDetails getUserDetails(CommonResponse<UserInfo> result)
    {
        UserInfo info = result.getBody();
        Set<String> dbAuthsSet = new HashSet<>();
        if (StringUtils.isNotEmpty(info.getRoles()))
        {
            // 获取角色
            dbAuthsSet.addAll(info.getRoles());
            // 获取权限
            dbAuthsSet.addAll(info.getPermissions());
        }
        //new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型
        //最终返回的String[]的长度是由你的list存储内容的长度决定了。
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        log.info("------------------------------------------------");
        log.info("{}getUserDetails----authorities",this.getClass().getName());
        log.info("{}",authorities);

        SysUser sysUser = info.getSysUser();
        log.info("------------------------用户基本信息--------------------------------------");
        log.info("{}", sysUser);
        return new LoginUser(sysUser.getId(), sysUser.getCompanyId(),sysUser.getSysRoles().get(0).getOrgId(),sysUser.getName(), sysUser.getPassword(), true, true, true, true,
                authorities);
    }
}
