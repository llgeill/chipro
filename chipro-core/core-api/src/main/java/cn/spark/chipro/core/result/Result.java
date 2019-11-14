package cn.spark.chipro.core.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author liliguang
 * @description
 * @date 2019-11-03 10:14:04
 */
@Data
@ToString
public class Result<T> implements Serializable {

    private T datas;

    private String msg;

    private Integer code;

    /**
     * 功能描述:  返回正确结果
     * <p>
     * 如果入参为空  或者 数组长度为0
     * 就认为  是未查询到数据
     *
     * @param t: 数据对象
     * @auther: liliguang
     * @date: 2019-11-03 10:14:29
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        if (t == null) {
            return noDataQueried(t);
        }

        if (t instanceof List) {
            List l = (List) t;
            if (l.size() == 0) {
                return noDataQueried(t);
            }
        }

        result.setCode(1);
        result.setMsg("成功");
        result.setDatas(t);
        return result;
    }


    /**
     * 功能描述：没查询到数据
     */
    public static Result noDataQueried() {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("没有查询到数据");
        return result;
    }


    /**
     * 功能描述：没查询到数据
     */
    public static <T> Result<T> noDataQueried(T t) {
        Result result = new Result();
        result.setCode(0);
        result.setDatas(t);
        result.setMsg("没有查询到数据");
        return result;
    }


    /**
     * 功能描述:  返回正确结果
     *
     * @auther: liliguang
     * @date: 2019-11-03 10:15:08
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMsg("成功");
        return result;
    }


    /**
     * 功能描述:    返回错误result对象 2
     *
     * @auther: liliguang
     * @date: 2019-11-03 10:15:20
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(2);
        result.setMsg("失败");
        return result;
    }

    /**
     * 功能描述:     返回错误result对象 默认响应代码 -1
     *
     * @param msg: 消息信息
     * @auther: liliguang
     * @date: 2019-11-03 10:15:33
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    /**
     * 功能描述:     返回错误result对象 默认错误代码 -1
     *
     * @param code: 响应代码
     * @param msg:  消息信息
     * @auther: liliguang
     * @date: 2019-11-03 10:15:45
     */
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
