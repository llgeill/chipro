package cn.spark.chipro.core.handle;

import cn.spark.chipro.core.result.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liliguang
 * @description
 * @date 2019/5/16
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if (cause instanceof InvalidTokenException) {
                response.getWriter().write(JSON.toJSONString(Result.error(10005, "无效的Token,请重新登录"), SerializerFeature.WriteMapNullValue));
            } else {
                response.getWriter().write(JSON.toJSONString(Result.error(10002, "Token不能为空"), SerializerFeature.WriteMapNullValue));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}