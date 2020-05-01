package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.api.model.params.LearnReportParam;
import cn.spark.chipro.manage.biz.entity.LearnReport;
import cn.spark.chipro.manage.biz.service.LearnReportService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 学情报告控制器
 *
 * @author 李利光
 * @Date 2020-04-12 11:06:37
 */
@Controller
@RequestMapping("/learnReport")
public class LearnReportController extends BaseController {


    @Autowired
    private LearnReportService learnReportService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody LearnReportParam learnReportParam) {
        this.learnReportService.add(learnReportParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody LearnReportParam learnReportParam) {
        this.learnReportService.update(learnReportParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody LearnReportParam learnReportParam) {
        this.learnReportService.delete(learnReportParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody LearnReportParam learnReportParam) {
        LearnReport detail = this.learnReportService.getById(learnReportParam.getLearnReportId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody LearnReportParam learnReportParam) {
        return this.learnReportService.findPageBySpec(learnReportParam);
    }

}


