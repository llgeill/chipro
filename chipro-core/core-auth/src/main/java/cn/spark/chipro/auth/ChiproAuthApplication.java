package cn.spark.chipro.auth;

import cn.spark.chipro.core.annotation.EnableDBClientDetailsService;
import cn.spark.chipro.core.annotation.EnableRedisTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@EnableDBClientDetailsService
@EnableRedisTokenStore
public class ChiproAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChiproAuthApplication.class, args);
    }

}
