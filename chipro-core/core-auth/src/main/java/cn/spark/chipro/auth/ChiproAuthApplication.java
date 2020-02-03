package cn.spark.chipro.auth;

import cn.spark.chipro.core.annotation.EnableDBClientDetailsService;
import cn.spark.chipro.core.annotation.EnableRedisTokenStore;
import cn.spark.chipro.core.config.ResourceServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDBClientDetailsService
@EnableRedisTokenStore
@EnableEurekaClient
@EnableSwagger2
public class ChiproAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChiproAuthApplication.class, args);
    }

}
