package cn.spark.chipro.test.biz.controller;

import cn.spark.chipro.core.util.UserContext;
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
public class TestController {

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
