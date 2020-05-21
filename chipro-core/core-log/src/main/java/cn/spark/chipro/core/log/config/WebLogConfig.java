package cn.spark.chipro.core.log.config;

import cn.spark.chipro.core.log.aop.WebLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebLogConfig {

    @Bean
    public WebLogAspect webLogAspect(){
        return new WebLogAspect();
    }
}
