package cn.spark.chipro.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: liliguang
 * @date: 2019-11-09 22:55
 * @description: 通过访问远程授权服务器 check_token 端点验证令牌，适用于jdbc存储或者内存存储的令牌方式，因为此方式与服务器关系紧密
 */
public class CustomRemoteTokenService {

    @Autowired
    RemoteTokenServices remoteTokenServices;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Resource(name = "authServerProp")
    private AuthorizationServerProperties authorizationServerProperties;

    @Bean(name = "authServerProp")
    public AuthorizationServerProperties authorizationServerProperties() {
        return new AuthorizationServerProperties();
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }


    @Bean
    @ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);
        return factory;
    }

    @Bean(name = "remoteTokenServices")
    @Primary
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())) {
            remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        }
        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientSecret())) {
            remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        }
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        remoteTokenServices.setRestTemplate(restTemplate);
        remoteTokenServices.setTokenName("xxx-llg");
        return remoteTokenServices;
    }

}
