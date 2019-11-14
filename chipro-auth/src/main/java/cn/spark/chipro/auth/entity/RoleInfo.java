package cn.spark.chipro.auth.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author liliguang
 * @description 用于oauth授权的用户角色类
 * @date 2019/11/10
 */
public class RoleInfo extends Role implements GrantedAuthority {

    @Override
    public String getAuthority() {

        return "ROLE_" + this.getRoleId();
    }
}
