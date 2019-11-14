package cn.spark.chipro.core.annotation;


import cn.spark.chipro.core.store.CustomRedisTokenStore;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomRedisTokenStore.class)
public @interface EnableDBTokenStore {
}
