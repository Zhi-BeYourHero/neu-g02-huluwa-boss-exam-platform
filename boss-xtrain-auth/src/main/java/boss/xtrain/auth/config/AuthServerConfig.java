package boss.xtrain.auth.config;

import boss.xtrain.auth.exception.CustomWebResponseExceptionTranslator;
import boss.xtrain.core.constant.CacheConstants;
import boss.xtrain.core.constant.SecurityConstants;
import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.service.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description OAuth2 认证服务配置
 * @create 2020-07-06
 * @since
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 认证管理器（AuthenticationManager）,处理用户的认证,
     * 其实还有一个决策管理器（AccessDecisionManager），来处理用户的权限
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 数据源，在配置文件中设置了，应该是通过
     * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceProperties}
     * 去配置文件来装配的
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * UserDetailsService实例，它负责根据用户名提取用户信息 UserDetails(包含密码)，
     * 而后DaoAuthenticationProvider会去对比UserDetailsService提取的用户密码与用户提交 的密码是否匹配作为认证成功的关键依据，
     * 因此可以通过将自定义的 UserDetailsService 公开为spring bean来定 义自定义身份验证。
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * spring-security-oauth2 中默认的 Token 生成方式是一个 UUID。
     * 实现 TokenEnhancer 接口。并重写它的 enhance 方法来生成一个新的令牌
     */
    @Autowired
    private TokenEnhancer tokenEnhancer;

    /**
     * 定义授权和令牌端点以及令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints
                // 请求方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 指定token存储位置
                .tokenStore(tokenStore())
                // 自定义生成令牌
                .tokenEnhancer(tokenEnhancer)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 是否重复使用 refresh_token
                .reuseRefreshTokens(false)
                // 自定义异常处理
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
    {
        //对外开放checkToken，先允许表单认证
        //检查token的接口默认是无法使用的，故进行开放
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    /**
     * 声明 ClientDetails实现
     */
    public RedisClientDetailsService clientDetailsService()
    {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        return clientDetailsService;
    }

    /**
     * 配置客户端详情
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在 这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * ClientDetailsService负责查找ClientDetails，而ClientDetails有几个重要的属性如下列表：
     * clientId：（必须的）用来标识客户的Id。
     * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
     * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
     * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
     * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.withClientDetails(clientDetailsService());
    }


    /**
     * 基于 Redis 实现，令牌保存到缓存
     */
    @Bean
    public TokenStore tokenStore()
    {
        //设置redis存
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        // 往token存的前缀
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    /**
     * 自定义生成令牌
     */
    @Bean
    public TokenEnhancer tokenEnhancer()
    {
        return new TokenEnhancer()
        {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication)
            {
                //如果是默认的uuid生成的token
                if (accessToken instanceof DefaultOAuth2AccessToken)
                {
                    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                    //getPrincipal():身份信息，大部分情况下返回的是UserDetails接口的实现类!!!
                    //UserDetails代表用户的详细信息，那从Authentication中取出来的UserDetails就是当前登录用户信息，它也是框架中的常用接口之一。
                    LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                    System.out.println("-----------------------------------------------");

                    System.out.println(user);
                    System.out.println("-----------------------------------------------");
                    //在这个Map中存放name和id
                    Map<String, Object> additionalInformation = new LinkedHashMap<>();
                    additionalInformation.put(SecurityConstants.DETAILS_USERNAME, authentication.getName());
                    additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                    additionalInformation.put(SecurityConstants.DETAILS_COMPANY_ID,user.getCompanyId());
                    additionalInformation.put(SecurityConstants.DETAILS_ORG_ID,user.getOrgId());
                    //设置到DefaultOAuth2AccessToken
                    token.setAdditionalInformation(additionalInformation);
                }
                return accessToken;
            };
        };
    }
}