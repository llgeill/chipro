package cn.spark.chipro.core.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义运行时异常
 * <p>
 * 继承RuntimeException 是为了对代码没有侵入性
 *
 * @author liliguang
 * @description
 * @date 2019-11-12 21:49
 */
@Slf4j
@Data
public class CoreException extends RuntimeException {

    /**
     * <code>异常代码</code>
     */
    private Integer code;

    /**
     * 功能描述:
     *
     * @param code:    异常代码
     * @param message: 异常信息
     * @return:
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public CoreException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 功能描述:空参构造
     *
     * @return:
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public CoreException() {
        super();
    }

    /**
     * 功能描述:构造方法
     *
     * @param message: 异常信息
     * @return:
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public CoreException(String message) {
        super(message);
    }

    /**
     * 功能描述:
     * 抛出自定义异常
     *
     * @param code: 异常代码
     * @return: void
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public static void throwException(Integer code) {
        CoreException coreException = new CoreException();
        coreException.setCode(code);
        log.error("抛出自定义异常----> throwException ----> code：{}", code);
        throw coreException;
    }

    /**
     * 功能描述:
     *
     * @param code:    异常代码
     * @param message: 异常信息
     * @return: void
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public static void throwException(Integer code, String message) {
        CoreException coreException = new CoreException(code, message);
        log.error("抛出自定义异常----> throwException ----> code：{} ,message：{}", code, message);
        throw coreException;
    }

    /**
     * 功能描述:
     * 抛出自定义异常
     *
     * @param message: 异常信息
     * @return: void
     * @auther: liliguang
     * @date: 2019-11-12 21:50
     */
    public static void throwExceptionMessage(String message) {
        CoreException coreException = new CoreException(message);
        log.error("抛出自定义异常----> throwException ----> message：{}", message);
        throw coreException;
    }
}
