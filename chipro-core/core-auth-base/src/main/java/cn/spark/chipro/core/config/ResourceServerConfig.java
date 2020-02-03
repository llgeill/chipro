package cn.spark.chipro.core.config;

import cn.spark.chipro.core.handle.AuthExceptionEntryPoint;
import cn.spark.chipro.core.handle.CustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 * @author liliguang
 * @description
 * @date 2019-11-07 20:45
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public ResourceServerConfig() {
        log.info("------------------------自动生成配置-------------------");
        log.info("------------------------自动生成配置-------------------");
        log.info("------------------------自动生成配置-------------------");
    }

    /**
     * 配置异常处理器端点
     *
     * @return
     */
    @Bean
    public AuthExceptionEntryPoint authExceptionEntryPoint() {
        return new AuthExceptionEntryPoint();
    }

    /**
     * 配置权限异常处理器端点
     *
     * @return
     */
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    /**
     * 配置自定义异常处理器
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authExceptionEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler());
    }

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

}
