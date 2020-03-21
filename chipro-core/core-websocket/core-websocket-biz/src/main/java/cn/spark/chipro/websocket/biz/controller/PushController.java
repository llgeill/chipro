package cn.spark.chipro.websocket.biz.controller;


import cn.spark.chipro.websocket.biz.entity.vo.MessageVO;
import cn.spark.chipro.websocket.biz.entity.vo.NotReadPageVO;
import cn.spark.chipro.websocket.biz.entity.vo.ReadMessageVO;
import cn.spark.chipro.websocket.biz.service.PushService;
import cn.spark.chipro.core.log.aop.WebLog;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author liliguang
 * @description 消息推送
 * @date 2020-03-22 00:42:08
 */
@Validated
@Api(tags = "消息推送")
@RestController
@RequestMapping("/message")
public class PushController {

    @Autowired
    private PushService pushService;

    /**
     * 推送
     *
     * @param message
     * @return
     */
    @WebLog(description = "推送", type = "insert")
    @ApiOperation("M1.推送")
    @PostMapping("/push")
    public Result push(@RequestBody MessageVO message) {
        try {
            pushService.push(message);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 查询未读消息
     *
     * @param vo 数据筛选对象
     * @return
     */
    @WebLog(description = "查询未读消息", type = "select")
    @ApiOperation("M2.查询未读消息")
    @PostMapping("/notReadList")
    public PageInfo notReadList(@RequestBody NotReadPageVO vo) {
        return pushService.notReadList(vo);
    }


    /**
     * 标记消息已读
     *
     * @param readMessage
     * @return
     */
    @WebLog(description = "阅读消息", type = "insert")
    @ApiOperation("M3.阅读消息")
    @PostMapping("/readMessage")
    public Result readMessage(@RequestBody ReadMessageVO readMessage) {

        return pushService.readMessage(readMessage);
    }
}
