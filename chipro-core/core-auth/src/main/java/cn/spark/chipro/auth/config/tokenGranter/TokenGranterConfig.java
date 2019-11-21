package cn.spark.chipro.auth.config.tokenGranter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @author liliguang
 * @description 自定义授权模式配置
 * @date 2019/11/8
 */
@Configuration
public class TokenGranterConfig {

    @Autowired
    DefaultTokenServices tokenService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public SmsCodeTokenGranter smsCodeTokenGranter() {
        return new SmsCodeTokenGranter(tokenService, clientDetailsService, new DefaultOAuth2RequestFactory(clientDetailsService));
    }
}
