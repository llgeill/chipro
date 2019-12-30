package cn.spark.chipro.core.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhichao
 * @description
 * @date 2019/4/18
 */
@Slf4j
@RestController
public class HystrixCommandController {

    @RequestMapping("/hystrixTimeout")
    public void hystrixTimeout(){
      log.error("触发断路由。。");
    }

    @HystrixCommand(commandKey = "authHystrixCommand")
    public void authHystrixCommand(){

        log.error("authHystrixCommand触发断路由。。");
    }
}
