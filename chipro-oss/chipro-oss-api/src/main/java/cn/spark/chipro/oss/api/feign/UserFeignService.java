package cn.spark.chipro.oss.api.feign;

import cn.spark.chipro.oss.api.constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户对外服务
 */
@FeignClient(name = ServiceNameConstant.USER_SERVICE)
@Component
public interface UserFeignService {

    @RequestMapping(value = "/user/getUserNameById", method = RequestMethod.POST)
    String getUserNameById();

}
