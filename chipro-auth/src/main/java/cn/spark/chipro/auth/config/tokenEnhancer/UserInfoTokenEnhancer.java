package cn.spark.chipro.auth.config.tokenEnhancer;

import cn.spark.chipro.auth.entity.UserInfo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liliguang
 * @description 用户信息增强, 由于base-auth配置会自动发现bean，所以无需重复配置
 * @date 2019/11/8
 */
@Component
public class UserInfoTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        OAuth2Request oAuth2Request = authentication.getOAuth2Request();
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        //将密码置空
        userInfo.setPassword(null);
        String clientId = oAuth2Request.getClientId();
        String userId = userInfo.getUserId();
        //将用户信息扩展到token
        Map<String, Object> additionalInfo = new HashMap(3);
        additionalInfo.put("user_name", userInfo);
        additionalInfo.put("code", 1);
        additionalInfo.put("msg", "登录成功");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;

    }
}
