package cn.spark.chipro.core.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.spark.chipro.core.gateway.exception.CheckedException;
import cn.spark.chipro.core.gateway.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

/**
 *  验证码过滤器
 *  @author lizhichao
 * @description
 * @date 2019/5/15
 */
@Slf4j
@Component
public class ImageCodeGatewayFilter extends AbstractGatewayFilterFactory {

    public static final String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";
    public static final String OAUTH_TOKEN_URL = "/oauth/token";
    private static final String BASIC_ = "Basic ";
    @Autowired
    private RedisTemplate redisTemplate;



    public static String[] extractAndDecodeHeader(String header) throws IOException, CheckedException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded = null;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var5) {
            CheckedException.throwException(10004,"Failed to decode basic authentication token: "+ var5.getLocalizedMessage());
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            CheckedException.throwException(10004,"Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
        return null;
    }

    public static String[] extractAndDecodeHeader(ServerHttpRequest request) throws IOException, CheckedException {
        String header = request.getHeaders().getFirst("Authorization");
        if (header != null && header.startsWith(BASIC_)) {
            return extractAndDecodeHeader(header);
        } else {
            CheckedException.throwException(10004,"请求头中client信息为空");
        }
        return null;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            //判断路径为 /oauth/token
            if (!(StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), new CharSequence[]{OAUTH_TOKEN_URL}))) {
                return chain.filter(exchange);
            }else {
                checkCode(request);
            }
            return  chain.filter(exchange);
        };
    }

    /**
     * 校验验证码是否正确
     * @param request
     */
    private void checkCode(ServerHttpRequest request){
        String code = request.getQueryParams().getFirst("code");
        MultiValueMap<String, String> queryParams = request.getQueryParams();

        System.out.println(queryParams);
        if (StrUtil.isBlank(code)) {
            ValidateCodeException.throwException(10020,"验证码为空！");
        } else {
            String randomStr = request.getQueryParams().getFirst("randomStr");
            if (StrUtil.isBlank(randomStr)) {
                ValidateCodeException.throwException(10020,"认证随机字符为空！");
            } else {
                String key = "DEFAULT_CODE_KEY" + randomStr;
                if (!this.redisTemplate.hasKey(key)) {
                    ValidateCodeException.throwException(10020,"验证码已过期");
                } else {
                    Object codeObj = this.redisTemplate.opsForValue().get(key);
                    if (codeObj == null) {

                    } else {
                        String saveCode = codeObj.toString();
                        if (StrUtil.isBlank(saveCode)) {
                            ValidateCodeException.throwException(10020,"验证码不正确");
                        } else if (!StrUtil.equals(saveCode, code)) {
                            ValidateCodeException.throwException(10020,"验证码不正确");
                        }
                        this.redisTemplate.delete(key);
                    }
                }
            }
        }
    }
}
