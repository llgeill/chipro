package cn.spark.chipro.core.web.common.config;

import cn.spark.chipro.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 异常拦截
 * @author liliguang
 * @description
 * @date 2019/4/11
 */
@Slf4j
@Component
@RestControllerAdvice
public class BindExceptionHanlder {

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = ex.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        // 生成返回结果

        return Result.error(10001,sb.toString());
    }

    @ExceptionHandler
    public Result handle(ValidationException exception) {
        String msg="";
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                msg = item.getMessage();
            }
        }
        return Result.error(msg);

    }

    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationBodyException(MethodArgumentNotValidException exception){
        AtomicReference<String> msg = new AtomicReference<>("");
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                msg.set(fieldError.getDefaultMessage());
            });

        }
        return Result.error(msg.get());
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public Result parameterTypeException(HttpMessageConversionException exception){
        log.error(exception.getLocalizedMessage());
        return Result.error("类型转换错误");

    }


    /**
     * 通用异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Result exception(Exception ex){
        String message = ex.getMessage();
        log.error("错误详情：{},{}", message,ex);
        return Result.error(10000,message);
    }
}
