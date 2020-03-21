package cn.spark.chipro.websocket.biz.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liliguang
 * @description
 * @date 2019/8/21 14:20
 */
@Data
public class ReadMessageVO {


    /**
     * 消息主键
     */
    @NotBlank(message = "消息ID不能为空")
    @ApiModelProperty("消息ID")
    private String messageId;

    /**
     * 用户主键
     */
    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private String userId;
}
