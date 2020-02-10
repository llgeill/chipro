package cn.spark.chipro.core.config;

import cn.spark.chipro.core.handle.AuthExceptionEntryPoint;
import cn.spark.chipro.core.handle.CustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 *
 * 基于 SecurityExpressionOperations 接口的表达式，也就是我们在上一文[3]的 javaConfig 配置。
 * 示例：@PreAuthorize("hasRole('ADMIN')") @PreAuthorize("hasAuthority('2')") 必须拥有 ROLE_ADMIN 角色。
 * 基于 UserDetails 的表达式，此表达式用以对当前用户的一些额外的限定操作。
 * 示例：@PreAuthorize("principal.username.startsWith('Felordcn')") 用户名开头为 Felordcn 的用户才能访问。
 * 基于对入参的 SpEL表达式处理。关于 SpEL 表达式可参考官方文档。或者通过关注公众号：Felordcn 来获取相关资料。
 * 示例：@PreAuthorize("#id.equals(principal.username)") 入参 id 必须同当前的用户名相同。
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
