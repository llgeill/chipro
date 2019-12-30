package cn.spark.chipro.core.gateway.handler;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码处理器
 * @author liliguang
 * @description
 * @date 2019-12-30 16:53
 */
@Slf4j
@Component
public class ImageCodeHandler implements HandlerFunction {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 这里使用 webflux
     * 处理验证码
     * @param serverRequest
     * @return
     */
    @Override
    public Mono handle(ServerRequest serverRequest) {
        String text = this.producer.createText();
        BufferedImage image = this.producer.createImage(text);
        String randomStr = serverRequest.queryParam("randomStr").get();
        this.redisTemplate.opsForValue().set("DEFAULT_CODE_KEY" + randomStr, text, 60L, TimeUnit.SECONDS);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException e) {
            log.error("ImageIO write err", e);
            return Mono.error(e);
        }finally {
            os.close();
        }

        return ServerResponse.ok().contentType(MediaType.IMAGE_JPEG).body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }
}
