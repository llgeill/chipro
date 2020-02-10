package cn.spark.chipro.auth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author liliguang
 * @description 用于spring security 授权认证的权限信息实体类
 * @date 2019/11/7
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class PermissionInfo extends Permission implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return super.getPermissionPath();
    }
}
