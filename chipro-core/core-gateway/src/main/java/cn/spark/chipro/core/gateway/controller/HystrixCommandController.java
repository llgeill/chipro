package cn.spark.chipro.core.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liliguang
 * @description
 * @date 2019/4/18
 */
@Slf4j
@RestController
public class HystrixCommandController {

    /**
     * 用于路由熔断后的熔断方法
     */
    @RequestMapping("/hystrixTimeout")
    public void hystrixTimeout(){
      log.error("触发断路由。。");
    }


}
