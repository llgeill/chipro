package cn.spark.chipro.auth.config.tokenGranter;


import cn.spark.chipro.auth.constant.SmsCodeConstant;
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
 * 扩展短信登录
 *
 * @author liliguang
 * @description
 * @date 2019-11-08 23:08
 */
@Slf4j
public class SmsCodeTokenGranter extends AbstractTokenGranter implements CustomTokenGranter {

    private static final String GRANT_TYPE = "sms_code";


    private boolean isDebug = log.isDebugEnabled();

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserMapper userMapper;


    public SmsCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        //获取请求参数
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        //客户端提交的手机号码
        String phone = parameters.get("phone");
        //客户端提交的验证码
        String smscode = parameters.get(GRANT_TYPE);
        if (StringUtil.isEmpty(phone)) {
            throw new InvalidGrantException("手机号不能为空");
        }
        //做具体的业务操作
        //String Idcard = userMapper.selectIdcardByPhone(phone);
        String Idcard = "";
        if (StringUtils.isEmpty(Idcard)) {
            if (isDebug) {
                log.debug("sms_code_没有绑定账号:[{}]", phone);
            }
            throw new InvalidGrantException("当前手机号码没有绑定账号");
        }
        // 从库里查用户
        UserDetails user = userDetailsService.loadUserByUsername(Idcard);
        if (user == null) {
            if (isDebug) {
                log.debug("用户不存在:[{}]", Idcard);
            }
            throw new InvalidGrantException("用户不存在");
        }
        //获取缓存key值
        String cachedKey = SmsCodeConstant.SMS_CODE_CACHE_PREFIX + phone;
        // 验证验证码
        String smsCodeCached = redisTemplate.opsForValue().get(cachedKey);
        if (StringUtil.isEmpty(smsCodeCached)) {
            if (isDebug) {
                log.debug("用户没有发送验证码_smsCodeCached:[{}]", smsCodeCached);
            }
            throw new InvalidGrantException("用户没有发送验证码");
        }

        if (StringUtil.isEmpty(smscode)) {
            if (isDebug) {
                log.debug("验证码不能为空");
            }
            throw new InvalidGrantException("验证码不能为空");
        }

        if (!smscode.equals(smsCodeCached)) {
            if (isDebug) {
                log.debug("验证码不匹配_smscode:[{}],smsCodeCached:[{}]", smscode, smsCodeCached);
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
