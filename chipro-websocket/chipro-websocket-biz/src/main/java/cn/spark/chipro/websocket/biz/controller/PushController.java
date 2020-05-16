package cn.spark.chipro.websocket.biz.controller;


import cn.spark.chipro.core.util.UserContext;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import cn.spark.chipro.websocket.api.model.vo.NotReadPageVO;
import cn.spark.chipro.websocket.api.model.vo.ReadMessageVO;
import cn.spark.chipro.websocket.biz.mapper.MessageMapper;
import cn.spark.chipro.websocket.biz.service.PushService;
import cn.spark.chipro.core.log.aop.WebLog;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private MessageMapper messageMapper;

    //@Autowired
    //private TccHmilyTestServiceImpl tccHmilyTestService;

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
            //普通推送
            message.setMessageTime(new Date());
            pushService.push(message);
            //Tcc改造后的推送服务
            //tccHmilyTestService.tryMethod(message);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 推送
     *
     * @param message
     * @return
     */
    @WebLog(description = "推送所有人", type = "insert")
    @ApiOperation("M1.推送")
    @PostMapping("/pushAll")
    public Result pushAll(@RequestBody MessageVO message) {
        try {
            String userId = (String) UserContext.getUserInfo().get("userId");
            List<String> userIDs = messageMapper.findUserIDs();
            List<String> collect = userIDs.stream().filter(s ->!s.equals(userId)).collect(Collectors.toList());
            //普通推送
            message.setMessageTime(new Date());
            message.setUserIds(collect);
            pushService.push(message);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 查询所有消息
     *
     * @param vo 用户id
     * @return
     */
    @WebLog(description = "查询所有消息", type = "select")
    @ApiOperation("M2.查询所有消息")
    @PostMapping("/allMessage")
    public Result allMessage(@RequestBody NotReadPageVO vo) {
        return Result.success(messageMapper.selectMessByUserId(vo.getUserId()));
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
