package cn.spark.chipro.websocket.api.feign;

import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.websocket.api.constant.ServiceNameConstant;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户对外服务
 */
@FeignClient(name = ServiceNameConstant.WEBSOCKET_SERVICE)
@Component
public interface WebSocketFeignService {

    /**
     * 消息推送
     * @param message
     * @return
     */
    @PostMapping("/message/push")
    public Result push(@RequestBody MessageVO message);

}
