package cn.spark.chipro.core.config;

import cn.spark.chipro.core.authProvider.CoreAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liliguang
 * @description 授权服务器走Spring security 验证流程
 * @date 2019-11-09 23:07
 */
@Configuration
@EnableWebSecurity
//如果加了-1的优先级，那么security过滤器将会服务auth的过滤器，导致一些纹理
//例如client无法验证
//@Order(-1)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置自定义认证器,当密码错误时抛出自定义异常
     *
     * @return
     */
    @Bean
    public CoreAuthenticationProvider coreAuthenticationProvider() {
        return new CoreAuthenticationProvider(userDetailsService);
    }

    /**
     * 使用默认的认证管理器
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }


    //采用bcrypt对密码进行编码
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义用户身份验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                //配置自定义用户认证，密码错误抛出自定义异常
                .authenticationProvider(coreAuthenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    //保护授权服务器的端点
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers
                        (
                                "/actuator/**",
                                "/oauth/logout",
                                "/user",
                                "/sendEmailCode/**",
                                "/sendSmsCode/**"
                        )
                .permitAll().and() // 忽略认证
                .csrf().disable()
                .httpBasic().and()
                .formLogin()
                .and()
                .authorizeRequests().anyRequest().authenticated();

    }

}
