package cn.spark.chipro.core.annotation;


import cn.spark.chipro.core.store.CustomRedisTokenStore;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author: liliguang
 * @date: 2019-11-10 09:45
 * @description: 在启动类上添加该注解来----开启redis存储token
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomRedisTokenStore.class)
public @interface EnableRedisTokenStore {
}
