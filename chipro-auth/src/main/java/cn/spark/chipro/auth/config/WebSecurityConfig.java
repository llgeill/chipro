package cn.spark.chipro.auth.config;

import cn.spark.chipro.core.config.CustomWebSecurityConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liliguang
 * @date: 2019-11-10 00:36
 * @description: 安全配置，保证端点受保护
 * 授权服务器是授权作用不是端点保护的，所以需要sceurity进行保护
 */
@Configuration
public class WebSecurityConfig extends CustomWebSecurityConfig {


}
