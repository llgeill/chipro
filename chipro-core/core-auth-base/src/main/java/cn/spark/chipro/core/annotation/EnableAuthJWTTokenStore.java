package cn.spark.chipro.core.annotation;


import cn.spark.chipro.core.store.CustomAuthJWTTokenStore;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liliguang
 * @date: 2019-11-10 09:46
 * @description: 在启动类上添加该注解来----开启 JWT 令牌存储（授权服务器-非对称加密）
 * 注意需要在服务器上配置相应的非对称加密文件
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomAuthJWTTokenStore.class)
public @interface EnableAuthJWTTokenStore {
}
