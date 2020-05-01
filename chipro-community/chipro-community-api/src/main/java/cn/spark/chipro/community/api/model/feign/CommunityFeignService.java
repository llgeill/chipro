package cn.spark.chipro.community.api.model.feign;

import cn.spark.chipro.community.api.model.constant.ServiceNameConstant;
import cn.spark.chipro.community.api.model.params.ProductionParam;
import cn.spark.chipro.community.api.model.result.ProductionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户对外服务
 */
@FeignClient(name = ServiceNameConstant.COMMUNITY_SERVICE)
@Component
public interface CommunityFeignService {

    @RequestMapping(value = "/production/productions", method = RequestMethod.POST)
    List<ProductionResult> productions(@RequestBody String userIds);

}
