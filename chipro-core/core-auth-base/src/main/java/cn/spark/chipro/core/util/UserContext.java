package cn.spark.chipro.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * 获取用户信息
 *
 * @author liliguang
 * @description
 * @date 2019-11-13 21:19
 */
public class UserContext {

    /**
     * 获取用户信息
     *
     * @return
     */
    public static Map getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Map) {
            Map user = (Map) principal;
            return user;
        }
        return null;
    }

}
