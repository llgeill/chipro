package cn.spark.chipro.core.web.common.config;

import cn.spark.chipro.core.web.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public BindExceptionHanlder bindExceptionHanlder(){
        return new BindExceptionHanlder();
    }
}
