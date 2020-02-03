package cn.spark.chipro.oss.biz.common.constant;

/**
 * 短信验证常量
 *
 * @author lizhichao
 * @description
 * @date 2019/6/27 11:32
 */
public interface SmsCodeConstant {
    /**
     * 验证码缓存前缀
     */
    String SMS_CODE_CACHE_PREFIX = "oss:sms_code_cache:";

    /**
     * 验证码缓存前缀
     */
    String EMAIL_CODE_CACHE_PREFIX = "oss:email_code_cache:";

    /**
     * 验证码有效时间 （天）
     */
    long SMS_CODE_CACHE_DURATION = 1;
}
