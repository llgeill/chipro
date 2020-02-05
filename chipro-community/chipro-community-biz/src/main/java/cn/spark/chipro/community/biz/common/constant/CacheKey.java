package cn.spark.chipro.community.biz.common.constant;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 *
 * @author liliguang
 * @date 2020-01-31 14:30
 */
public interface CacheKey {

    /**
     * 用户账号缓存
     */
    String USER_REPEAT_ACCOUNT = "user_repeat_account";

    /**
     * 用户别名缓存
     */
    String USER_ALIAS_NAME = "user_alias_name";


}
