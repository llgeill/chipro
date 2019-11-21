package cn.spark.chipro.core.web.common.config;

import cn.spark.chipro.core.config.ResourceServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author liliguang
 * @description 资源服务器配置
 * @date 2019/11/13
 */
@Configuration
@ConditionalOnMissingBean(ResourceServerConfig.class)
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
