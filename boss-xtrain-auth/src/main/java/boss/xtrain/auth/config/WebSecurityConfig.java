package boss.xtrain.auth.config;

import boss.xtrain.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description Security 安全认证相关配置,Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 *              Order注解定义注释组件的排序顺序，越小表示优先级越高
 * @create 2020-07-06
 * @since
 */
@Order(99)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * 配置用户信息服务,实现类在工具包
     * {@link UserDetailsServiceImpl}
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 密码编码格式，
     * BCryptPasswordEncoder采用SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，
     * 使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                //authorizeRequests()：允许基于使用HttpServletRequest限制访问
                .authorizeRequests()
                //白名单，无需认证授权
                .antMatchers(
                        "/actuator/**",
                        //授权
                        "/oauth/*",
                        //token开头的是主要是用于logout，见TokenController
                        "/token/**").permitAll()
                .anyRequest().authenticated()
                //.and().csrf().disable()：关闭跨站请求伪造,方便postman请求（默认开启）
                .and().csrf().disable();
    }
}