package cn.spark.chipro.auth.config;

import cn.spark.chipro.core.config.AuthorizationServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;


/**
 * @author liliguang
 * @description 授权服务器配置
 * @date 2019-11-07 22:49
 */
@Slf4j
@Configuration
public class AuthServerConfig extends AuthorizationServerConfig {

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * 调用父类构造函数设置令牌相关属性
     */
    public AuthServerConfig() {

        super(
                //令牌失效时间
                (int) TimeUnit.DAYS.toSeconds(3),
                //刷新令牌失效时间
                (int) TimeUnit.DAYS.toSeconds(7),
                //是否可以重用刷新令牌
                false,
                //是否支持刷新令牌
                true
        );
    }


}
