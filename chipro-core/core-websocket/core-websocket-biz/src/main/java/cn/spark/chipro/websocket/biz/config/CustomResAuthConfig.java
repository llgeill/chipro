package cn.spark.chipro.websocket.biz.config;

import cn.spark.chipro.core.config.ResourceServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liliguang
 * @description 资源服务器配置
 * @date 2019/11/13
 */
@Configuration
public class CustomResAuthConfig extends ResourceServerConfig {


    /**
     * Http安全配置，对每个到达系统的http请求链接进行校验
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有请求必须认证通过
        http.authorizeRequests().antMatchers(
                "/v2/api-docs",
                "/ws/**",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/course/coursebase/**",
                "/webjars/**",
                "/actuator/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
