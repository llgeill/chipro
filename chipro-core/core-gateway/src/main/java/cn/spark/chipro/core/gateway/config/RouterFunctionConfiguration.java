package cn.spark.chipro.core.gateway.config;

import cn.spark.chipro.core.gateway.handler.ImageCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * webflux控制器路由配置
 *
 * @author liliguang
 * @description
 * @date 2019-12-30 16:52
 */
@Configuration
public class RouterFunctionConfiguration {

    @Autowired
    private ImageCodeHandler imageCodeHandler;

    /**
     *将对应处理器 映射到 对应路径
     * @return
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/code")
                 .and(RequestPredicates.accept(MediaType.IMAGE_JPEG)), this.imageCodeHandler);
    }
}
