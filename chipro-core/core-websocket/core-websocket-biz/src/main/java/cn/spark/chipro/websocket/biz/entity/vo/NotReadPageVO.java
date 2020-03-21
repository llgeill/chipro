package cn.spark.chipro.websocket.biz.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liliguang
 * @description 未读消息查询
 * @date 2019/8/21 11:41
 */
@ApiModel("未读消息查询")
@Data
public class NotReadPageVO {

    @ApiModelProperty("消息类型")
    private String messageType;

    @ApiModelProperty("用户ID")
    private String userId;
}
