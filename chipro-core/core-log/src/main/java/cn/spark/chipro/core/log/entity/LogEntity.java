package cn.spark.chipro.core.log.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lizhichao
 * @description
 * @date 2019/4/13
 */
@Data
@ToString
public class LogEntity implements Serializable {


    /**
     *
     *类名
     */
    private String className;

    /**
     *
     *方法名
     */
    private String methodName;

    /**
     *
     * 类型
     */
    private String type;

    /**
     *
     * 方法描述
     */
    private String methodDescription;

    /**
     * 类描叙
     */
    private String classDescription;

    /**
     *
     * 请求参数
     */
    private String params;

    /**
     *
     * 响应数据
     */
    private String result;

    /**
     * 消耗时间
     */
    private Long spendTimeMillis;


    /**
     * url
     */
    private String url;

    /**
     * 请求方式
     */
    private String httpMethod;

    /**
     * IP地址
     */
    private String ip;


    /**
     * 请求头
     */
    private Map<String,String> headers;


    public LogEntity() {
    }


    public LogEntity(String className, String methodName, String type, String params, String result, Long spendTimeMillis, String url, String httpMethod, String ip, Map headers) {
        this.className = className;
        this.methodName = methodName;
        this.type = type;
        this.params = params;
        this.result = result;
        this.spendTimeMillis = spendTimeMillis;
        this.url = url;
        this.httpMethod = httpMethod;
        this.ip = ip;
        this.headers = headers;
    }
}
