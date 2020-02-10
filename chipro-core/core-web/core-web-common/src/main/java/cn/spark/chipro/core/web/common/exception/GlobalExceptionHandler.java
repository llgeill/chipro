/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.spark.chipro.core.web.common.exception;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author liliguang
 * @date 2020-02-07 15:50
 */
@ControllerAdvice
@Order(200)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(CoreException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result coreException(CoreException e) {
        return Result.error(e.getCode(),e.getMessage());
    }



    /**
     * 权限异常处理
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        return Result.error(10019,"权限不足");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return Result.error(-1,"服务器未知运行时异常");
    }


}
