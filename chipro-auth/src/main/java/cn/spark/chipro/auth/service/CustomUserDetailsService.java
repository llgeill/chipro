package cn.spark.chipro.auth.service;


import cn.spark.chipro.auth.entity.PermissionInfo;
import cn.spark.chipro.auth.entity.RoleInfo;
import cn.spark.chipro.auth.entity.UserInfo;
import cn.spark.chipro.auth.mapper.UserMapper;
import cn.spark.chipro.core.exception.BootOAuth2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liliguang
 * @description 用户登录实现
 * @date 2019/4/17
 */
@Slf4j
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    JdbcClientDetailsService clientDetailsService;


    @Resource
    private UserMapper userMapper;

    /**
     * 用户认证
     *
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //取出身份，如果身份为空说明没有认证
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String clientId = null;
//        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
//        if(authentication==null){
//            ClientDetails clientDetails = null;
//            try {
//                clientDetails  = clientDetailsService.loadClientByClientId(s);
//            }catch (Exception e){
//                log.error("没有basic 认证获取不到 clientId");
//            }
//            if(clientDetails!=null){
//                //密码
//                String clientSecret = clientDetails.getClientSecret();
//                Collection<GrantedAuthority> authorities = clientDetails.getAuthorities();
//                return new User(s,clientSecret, authorities);
//            }
//        }else {
//            //获取clientId
//            clientId =  ((User)authentication.getPrincipal()).getUsername();
//        }getUsername
        log.info("userDetailsServiceImpl--->用户认证--->用户名 : {}", s);
        //从数据库获取用户信息
        UserInfo userInfo = userMapper.selectUserByUserName(s);
        if (userInfo == null) {
            throw new BootOAuth2Exception("用户不存在");/**/
        }
        //失效时间
        Date failureTime = userInfo.getFailureTime();
        if (!isFailure(failureTime)) {
            log.warn("userDetailsServiceImpl---->用户已失效----> 失效时间 :{}");
            throw new BootOAuth2Exception("用户已失效");
        }
        //创建用户权限数组
        List<GrantedAuthority> authorities = new ArrayList<>();
        //查找当前用户拥有的角色
        List<RoleInfo> roles = userMapper.selectRoleByUserId(userInfo.getUserId());
        //查询 用户权限
        List<PermissionInfo> permissions = userMapper.selectPermissionByUserId(userInfo.getUserId());
        //将查询权限封装到用户权限数组中
        authorities.addAll(roles);
        authorities.addAll(permissions);
        //将权限封装到用户信息中
        userInfo.setAuthorities(authorities);
        log.info("userDetailsServiceImpl---> 获取到用户信息：{}", userInfo);
        return userInfo;
    }


    /**
     * 判断 用户是否过期
     *
     * @param failureTime
     * @return
     */
    private boolean isFailure(Date failureTime) {
        if (failureTime == null) {
            return false;
        }
        //当前时间
        Date date = new Date();
        return date.before(failureTime);
    }


}
