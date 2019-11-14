package cn.spark.chipro.core.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author: liliguang
 * @date: 2019-11-09 22:15
 * @description: 使用redis存取令牌
 */
public class CustomRedisTokenStore {

    /**
     * 如何实现用户挤下线？
     * <p>
     * 继承tokenStore重写getAccessToken方法返回null即可
     * <p>
     * 由于 每次调用登录接口 会从缓存中读取token信息
     * <p>
     * 返回null 是说明 缓存中未读取到
     * <p>
     * tokenService 会重新生成一个新的 token
     */


    @Autowired(required = true)
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
