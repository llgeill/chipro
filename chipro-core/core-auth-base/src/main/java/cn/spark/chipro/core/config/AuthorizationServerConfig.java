package cn.spark.chipro.core.config;

import cn.spark.chipro.core.exception.BootOAuth2WebResponseExceptionTranslator;
import cn.spark.chipro.core.tokenGranter.CustomTokenGranter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


/**
 * 授权服务器配置
 *
 * @author liliguang
 * @description
 * @date 2019-11-07 20:45
 */
@Slf4j
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //令牌失效时间
    private int accessTokenValiditySeconds;
    //刷新令牌失效时间
    private int refreshTokenValiditySeconds;
    //是否可以重用刷新令牌
    private boolean isReuseRefreshToken;
    //是否支持刷新令牌
    private boolean isSupportRefreshToken;

    /**
     * Spring容器
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * token存储方案
     */
    @Autowired
    private TokenStore tokenStore;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 自定义用户认证服务
     */

    @Qualifier(value = "customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 客户端认证服务(jdbc解决方案)
     */
    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    /**
     * 通过构造方法设置token属性
     *
     * @param accessTokenValiditySeconds  令牌失效时间
     * @param refreshTokenValiditySeconds 刷新令牌失效时间
     * @param isReuseRefreshToken         是否可以重用刷新令牌
     * @param isSupportRefreshToken       是否支持刷新令牌
     */
    public AuthorizationServerConfig(int accessTokenValiditySeconds, int refreshTokenValiditySeconds, boolean isReuseRefreshToken, boolean isSupportRefreshToken) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.isReuseRefreshToken = isReuseRefreshToken;
        this.isSupportRefreshToken = isSupportRefreshToken;
    }

    /**
     * 自定义异常处理器
     */
    @Bean
    public BootOAuth2WebResponseExceptionTranslator bootWebResponseExceptionTranslator() {

        return new BootOAuth2WebResponseExceptionTranslator();
    }

    /**
     * 配置授权服务器端点的安全
     *
     * @param oauthServer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置授权断点相关处理策略
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        //设置授权端点相关的策略
        endpoints
                //设置认证管理器
                .authenticationManager(authenticationManager)
                //设置授权模式
                .tokenGranter(tokenGranter())
                // 设了 tokenGranter 后该配制失效,需要在 tokenServices() 中设置
                //.tokenEnhancer(tokenEnhancer())
                //设置自定义用户验证方案
                .userDetailsService(userDetailsService)
                //设置自定义的token服务
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(bootWebResponseExceptionTranslator());

    }


    /**
     * 配置TokenService处理类及其相关的处理行为,默认处理类
     * 1.配置一个或多个令牌增强器
     * 2.配置token过期、刷新等属性
     * 3.配置token存储策略
     * 4.配置客户端用户认证方案
     *
     * @return
     */
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        //通过spring容器直接获取TokenEhancer的所有Bean实现类
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        //配置token增强链，链式调用累加增强令牌信息
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        //将TokenEhancer数组放到token增强链中
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
        //通过token服务设置token相关属性
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(isReuseRefreshToken);
        defaultTokenServices.setSupportRefreshToken(isSupportRefreshToken);
        defaultTokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        defaultTokenServices.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        //通过token服务设置token存储方案
        defaultTokenServices.setTokenStore(tokenStore);
        //将token增强链放入token服务中
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        //若通过 JDBC 存储令牌
        if (Objects.nonNull(jdbcClientDetailsService)) {
            defaultTokenServices.setClientDetailsService(jdbcClientDetailsService);
        }
        return defaultTokenServices;
    }

    /**
     * 配置token授权链，通过grant_type匹配授权模式
     *
     * @return
     */
    private List<TokenGranter> getDefaultTokenGranters() {
        ClientDetailsService clientDetails = jdbcClientDetailsService;
        AuthorizationServerTokenServices tokenServices = defaultTokenServices();
        AuthorizationCodeServices authorizationCodeServices = new InMemoryAuthorizationCodeServices();
        OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(jdbcClientDetailsService);

        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
        // 添加授权码模式
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails, requestFactory));
        // 添加刷新令牌的模式
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        // 添加隐式授权模式
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory));
        // 添加客户端模式
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        if (authenticationManager != null) {
            // 添加密码模式
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetails, requestFactory));
        }
        //通过spring容器直接获取TokenGranters的所有Bean实现类
        Collection<CustomTokenGranter> customTokenGranters = applicationContext.getBeansOfType(CustomTokenGranter.class).values();
        //添加一个或多个自定义授权模式
        tokenGranters.addAll(customTokenGranters);

        return tokenGranters;
    }


    /**
     * 通过 tokenGranter List<TokenGranter>
     */
    private TokenGranter tokenGranter() {
        TokenGranter tokenGranter = new TokenGranter() {
            private CompositeTokenGranter delegate;

            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate == null) {
                    delegate = new CompositeTokenGranter(getDefaultTokenGranters());
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
        return tokenGranter;
    }


}
