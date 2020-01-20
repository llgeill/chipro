package cn.spark.chipro.test.biz.controller;

import cn.spark.chipro.core.util.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liliguang
 * @description 资源服务器测试
 * @date 2019/11/13
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${llg}")
    private String from;

    @RequestMapping("/from")
    public String from(){

        return this.from;
    }

    @RequestMapping("token")
    public String testToken() {
        return "token";
    }

    @RequestMapping("noToken")
    public String testNoToken() {
        Map map = UserContext.getUserInfo();
        return "noToken";
    }
}
