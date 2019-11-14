package cn.spark.chipro.auth.endpoint;


import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liliguang
 * @description 自定义控制器
 * @date 2019-11-03 10:42:15
 */
@Slf4j
@RestController
public class CoreTokenEndpoint {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private TokenStore tokenStore;


    /**
     * 退出登录
     *
     * @param authHeader
     * @return
     */
    @GetMapping("/oauth/logout")
    public Result loginOut(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        //判断请求头是否有token
        if (StringUtils.hasText(authHeader)) {
            //获取token值
            String tokenValue = authHeader.replace("Bearer", "").trim();
            //如果为空则直接返回
            if (StringUtil.isEmpty(tokenValue)) {
                return Result.error("退出失败，token 为空");
            }
            //移除token
            if (!consumerTokenServices.revokeToken(tokenValue)) {
                log.info("注销失败--->token：{}", tokenValue);
                return Result.error("退出失败");
            }
            log.info("注销成功--->token：{}", tokenValue);
        }
        return Result.success("退出成功");
    }

    /**
     * 获取用户信息,用于资源服务器的user-info-uri配置
     * 但此接口自行实现验证token并且获取用户认证信息
     *
     * @param authorization
     * @return
     */
    @GetMapping("/user")
    public Map principal(@RequestHeader String authorization) {

        Map<String, Object> map = new HashMap<>();
        OAuth2Authentication authen = null;
        try {
            authen = tokenStore.readAuthentication(authorization.split(" ")[1]);
            if (authen == null) {
                map.put("error", "invalid token !");
                return map;
            }
        } catch (Exception e) {
            System.out.println(e);
            map.put("error", e);
            return map;
        }
        //注意这两个key都不能随便填，都是和客户端进行数据处理时进行对应的。
        map.put("user", authen.getPrincipal());
        map.put("authorities", authen.getAuthorities());
        return map;
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号码
     * @return
     */
    @GetMapping("/sendSmsCode/{phone}")
    public Result sendSmsCode(@PathVariable(value = "phone") String phone) {
        try {
            //发送短信服务，待完善
            //smsService.sendSmsCode(phone);
        } catch (CoreException e) {
            return Result.error(e.getCode(), e.getMessage());
        }
        return Result.success("发送成功");
    }


}
