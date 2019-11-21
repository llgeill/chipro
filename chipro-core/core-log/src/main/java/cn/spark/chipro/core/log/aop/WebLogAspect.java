package cn.spark.chipro.core.log.aop;



import cn.spark.chipro.core.log.entity.LogEntity;
import cn.spark.chipro.core.log.service.LogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 切面解析WebLog注解
 * @author lizhichao
 * @description
 * @date 2019/4/13
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class WebLogAspect {



    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    ThreadLocal<Long> stopTime = new ThreadLocal<Long>();

    @Autowired(required = false)
    private LogService logService;

    @Around("@annotation(WebLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable { // ProceedingJoinPoint


        // 为JoinPoint
        // 的子类，在父类基础上多了proceed()方法，用于增强切面
        // 请求开始时间
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // java reflect相关类，通过反射得到注解
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();


        // 如果类上WebLog注解不为空，获取相关内容
        String[] classLog = webLogGetValue(targetClass.getAnnotation(WebLog.class));

        // 如果方法上WebLog注解不为空，获取相关内容
        String[] methodLog = webLogGetValue(method.getAnnotation(WebLog.class));

        //日志类型
        String type = methodLog[0];

        // 目标类名
        String className = targetClass.getName() ;
        // 参数名
        String methodName= method.getName();


        LogEntity logEntity = new LogEntity();
        logEntity.setUrl(request.getRequestURI());
        logEntity.setHttpMethod(request.getMethod());
        logEntity.setIp(request.getRemoteAddr());
        Map<String, String> headers = new HashMap<>();
        logEntity.setHeaders(headers);

        //请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers.put(headerName,request.getHeader(headerName));
        }
        logEntity.setClassDescription(classLog[1]);
        logEntity.setMethodDescription(methodLog[1]);
        logEntity.setClassName(className);
        logEntity.setMethodName(methodName);
        logEntity.setType(StringUtils.isEmpty(classLog[0])?methodLog[0]:classLog[0]);

        //获取返回值
        Object result = null;

        if(joinPoint!=null){
            //请求参数
            if (joinPoint.getArgs() !=null ){
                Object[] argsArr=joinPoint.getArgs();
                //获取参数
                String  params = JSON.toJSONString(argsArr);
                logEntity.setParams(params);
            }
            //打印请求信息
            writeRequestLog(logEntity);

            // 直接执行 proceed()
            result= joinPoint.proceed();
        }


        // 请求结束时间
        stopTime.set(System.currentTimeMillis());
        // 请求消耗时间
        long spendTimeMillis = (stopTime.get() - startTime.get());

        startTime.remove();
        stopTime.remove();

        logEntity.setSpendTimeMillis(spendTimeMillis);
        logEntity.setResult(JSON.toJSONString(result));

        writeResponseLog(logEntity);

        //需要写日志
        if(logService!=null){
            writeLog(logEntity);
        }

        return result;
    }


    /**
     * 功能描述:
     *         保存日志数据,由业务组件实现
     * @param logEntity: 日志实体
     * @auther: lizhichao
     * @date: 2019/4/13 15:39
     */
    private void writeLog(LogEntity logEntity) {
        logService.writeLog(logEntity);
    }

    /**
     * 功能描述:
     *          打印响应日志
     * @param logEntity:
     * @auther: lizhichao
     * @date: 2019/4/13 15:37
     */
    private void writeResponseLog(LogEntity logEntity){
        log.info("=========================================响应日志-开始=========================================");
        log.info("|| 消耗时间：[{}ms] ", logEntity.getSpendTimeMillis());
        log.info("|| 响应数据：[{}] ",  logEntity.getResult());
        log.info("=========================================响应日志-结束=========================================");
    }

    /**
     * 打印请求日志
     * @param logEntity
     */
    private void writeRequestLog(LogEntity logEntity){

        log.info("=========================================请求日志-开始=========================================");
        log.info("|| URL：[{}]",logEntity.getUrl());
        log.info("|| HTTP Method：[{}]",logEntity.getHttpMethod());
        log.info("|| IP：[{}]",logEntity.getIp());

        Map<String, String> headers = logEntity.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            log.info("|| Header >> [{}]：[{}]",entry.getKey(),entry.getValue());
        }
        log.info("|| 类描述：[{}]",logEntity.getClassDescription());
        log.info("|| 方法描述：[{}]",logEntity.getMethodDescription());
        log.info("|| 操作类型：[{}]",logEntity.getType());
        log.info("|| 调用接口：[{}]-->[{}]", logEntity.getClassName(),logEntity.getMethodName());
        log.info("|| 请求参数：[{}] ", logEntity.getParams());
        log.info("=========================================请求日志-结束=========================================");


    }

    private String[]  webLogGetValue(WebLog webLog){
        String[] r = new String[2];
        if (webLog != null) {
            if(!StringUtils.isEmpty(webLog.type())){
                r[0]=webLog.type();
            }
            if(!StringUtils.isEmpty(webLog.description())){
                r[1]=webLog.description();
            }
        }
        return r;
    }

}
