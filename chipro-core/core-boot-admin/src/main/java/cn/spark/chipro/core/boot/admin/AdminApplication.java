package cn.spark.chipro.core.boot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lizhichao
 * @description spring boot admin
 * @date 2019/4/3
 */

@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
