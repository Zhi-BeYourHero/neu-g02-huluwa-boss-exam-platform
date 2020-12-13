package boss.xtrain.security.config;

import boss.xtrain.core.constant.SecurityConstants;
import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.util.text.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description https://my.oschina.net/giegie/blog/3023768 根据checktoken 的结果转化用户信息
 * @create 2020-07-06
 * @since
 */
public class CommonUserConverter implements UserAuthenticationConverter
{
    private static final String N_A = "N/A";
    Logger log = LoggerFactory.getLogger(CommonUserConverter.class);
    /**
     * 将授权信息返回到资源服务
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication)
    {
        Map<String, Object> authMap = new LinkedHashMap<>();
        authMap.put(USERNAME, userAuthentication.getName());
        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty())
        {
            authMap.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));
        }
        return authMap;
    }

    /**
     * 获取用户认证信息
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map)
    {

        if (map.containsKey(USERNAME))
        {
            log.info(map.toString());
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            log.info("{}","资源服务器获取认证信息，Class："+this.getClass().getName() + "：Method：extractAuthentication");
            authorities.forEach(x->{
                log.info("拥有的权限：{}",x.toString());
            });
            Long userId = Convert.toLong(map.get(SecurityConstants.DETAILS_USER_ID));
            String username = (String) map.get(SecurityConstants.DETAILS_USERNAME);
            Long companyId = Convert.toLong(map.get(SecurityConstants.DETAILS_COMPANY_ID));
            Long orgId = Convert.toLong(map.get(SecurityConstants.DETAILS_ORG_ID));
            LoginUser user = new LoginUser(userId, companyId,orgId,username, N_A, true, true, true, true, authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
        }
        return null;
    }

    /**
     * 获取权限资源信息
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map)
    {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String)
        {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection)
        {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
