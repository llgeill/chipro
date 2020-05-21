package cn.spark.chipro.test.controller;


import cn.spark.chipro.test.entity.Test;
import cn.spark.chipro.test.model.params.TestParam;
import cn.spark.chipro.test.model.result.TestResult;
import cn.spark.chipro.test.service.TestService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import cn.spark.chipro.test.service.impl.TccHmilyTestServiceImpl;
import cn.spark.chipro.websocket.api.feign.WebSocketFeignService;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/test";

    @Autowired
    private TestService testService;

    @Autowired
    private TccHmilyTestServiceImpl tccHmilyTestService;



    /**
     * 测试分布式事务
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/dt")
    @ResponseBody
    public Result dt() {
        testService.dt();
        return Result.success();
    }

    /**
     * 测试分布式事务
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/tccdt")
    @ResponseBody
    public Result tccdt() {
        tccHmilyTestService.tryMethod();
        return Result.success();
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(TestParam testParam) {
        this.testService.add(testParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(TestParam testParam) {
        this.testService.update(testParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(TestParam testParam) {
        this.testService.delete(testParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(TestParam testParam) {
        Test detail = this.testService.getById(testParam.getTestId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(TestParam testParam) {
        return this.testService.findPageBySpec(testParam);
    }

}


