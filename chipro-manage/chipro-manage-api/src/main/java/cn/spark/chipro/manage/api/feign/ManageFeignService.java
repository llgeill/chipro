package cn.spark.chipro.manage.api.feign;

import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.manage.api.constant.ServiceNameConstant;
import cn.spark.chipro.manage.api.model.params.ClassUserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户对外服务
 */
@FeignClient(name = ServiceNameConstant.MANAGE_SERVICE,url = "http://localhost:1777")
@Component
public interface ManageFeignService {


    @RequestMapping(value = "/classUser/batchAdd", method = RequestMethod.POST)
    Result batchAdd(@RequestBody List<ClassUserParam> classUserParamList);

}
