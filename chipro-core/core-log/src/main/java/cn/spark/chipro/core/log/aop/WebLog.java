package cn.spark.chipro.core.log.aop;

import java.lang.annotation.*;

/**
 * @author liliguang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited  //可以继承
public @interface WebLog {
	
	
	/**
	 * 类型
	 * @return
	 */
	 String type() default "";
	
	 /**
     * 描述
     */
	 String description() default "";
    

    

}
