package cn.spark.chipro.auth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author liliguang
 * @description 用于spring security授权认证的用户信息实体类
 * @date 2019/11/7
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class UserInfo extends User implements UserDetails {

    /**
     * 用于securtiy认证
     * 权限，角色
     */
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }


    /**
     * 指示是否已过期的用户的账号,过期的账号无法认证
     *
     * @return true则为永不过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     *
     * @return ture则为不锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return true注则为不过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户账号是否可以启用(可以通过数据库用户表提供启用字段来判断账号是否启动)
     *
     * @return true则为永久启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
