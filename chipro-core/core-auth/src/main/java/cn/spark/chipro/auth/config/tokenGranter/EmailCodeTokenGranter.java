package cn.spark.chipro.auth.config.tokenGranter;


import cn.spark.chipro.auth.constant.SmsCodeConstant;
import cn.spark.chipro.auth.entity.UserInfo;
import cn.spark.chipro.auth.mapper.UserMapper;
import cn.spark.chipro.core.tokenGranter.CustomTokenGranter;
import cn.spark.chipro.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 扩展邮箱登录
 *
 * @author liliguang
 * @description
 * @date 2019-11-08 23:08
 */
@Slf4j
public class EmailCodeTokenGranter extends AbstractTokenGranter implements CustomTokenGranter {

    private static final String GRANT_TYPE = "email_code";


    private boolean isDebug = log.isDebugEnabled();

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserMapper userMapper;


    public EmailCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
                                 ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        //获取请求参数
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        //客户端提交的邮箱
        String email = parameters.get("email");
        //客户端提交的验证码
        String emailCode = parameters.get(GRANT_TYPE);
        if (StringUtil.isEmpty(email)) {
            throw new InvalidGrantException("邮箱号不能为空");
        }
        //做具体的业务操作
        UserInfo userInfo = userMapper.selectUserByEmail(email);
        if (userInfo == null) {
            if (isDebug) {
                log.debug("email_code_没有绑定账号:[{}]", email);
            }
            throw new InvalidGrantException("当前邮箱没有绑定账号");
        }
        // 从库里查用户
        UserDetails user = userDetailsService.loadUserByUsername(email);
        if (user == null) {
            if (isDebug) {
                log.debug("用户不存在:[{}]", email);
            }
            throw new InvalidGrantException("用户不存在");
        }
        //获取缓存key值
        String cachedKey = SmsCodeConstant.EMAIL_CODE_CACHE_PREFIX + email;
        // 验证验证码
        String smsCodeCached = redisTemplate.opsForValue().get(cachedKey);
        if (StringUtil.isEmpty(smsCodeCached)) {
            if (isDebug) {
                log.debug("用户没有发送验证码_smsCodeCached:[{}]", smsCodeCached);
            }
            throw new InvalidGrantException("用户没有发送验证码");
        }

        if (StringUtil.isEmpty(emailCode)) {
            if (isDebug) {
                log.debug("验证码不能为空");
            }
            throw new InvalidGrantException("验证码不能为空");
        }

        if (!emailCode.equals(smsCodeCached)) {
            if (isDebug) {
                log.debug("验证码不匹配_emailcode:[{}],emailCodeCached:[{}]", emailCode, smsCodeCached);
            }
            throw new InvalidGrantException("验证码不正确或已失效");
        } else {
            //删除验证码
            redisTemplate.delete(cachedKey);
        }
        //创建认证体
        Authentication userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // 关于user.getAuthorities(): 我们的自定义用户实体是实现了
        // org.springframework.security.core.userdetails.UserDetails 接口的, 所以有 user.getAuthorities()
        // 当然该参数传null也行
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        //继续创建oauth2请求，将认证信息和请求继续封装
        OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(oAuth2Request, userAuth);
    }
}
