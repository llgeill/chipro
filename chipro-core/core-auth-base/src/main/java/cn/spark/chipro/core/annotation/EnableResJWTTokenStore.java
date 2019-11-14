package cn.spark.chipro.core.annotation;


import cn.spark.chipro.core.store.CustomResJWTTokenStore;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liliguang
 * @date: 2019-11-10 09:46
 * @description: 在启动类上添加该注解来----开启 JWT 令牌存储（资源服务器-非对称加密）
 * 注意需要将公钥放到resource文件夹上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomResJWTTokenStore.class)
public @interface EnableResJWTTokenStore {
}
