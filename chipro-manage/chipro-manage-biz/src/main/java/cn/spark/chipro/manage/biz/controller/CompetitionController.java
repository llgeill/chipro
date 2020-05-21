package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Competition;
import cn.spark.chipro.manage.api.model.params.CompetitionParam;
import cn.spark.chipro.manage.api.model.result.CompetitionResult;
import cn.spark.chipro.manage.biz.service.CompetitionService;
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
 * 控制器
 *
 * @author LCF
 * @Date 2020-04-11 09:53:35
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends BaseController {


    @Autowired
    private CompetitionService competitionService;

    /**
     * 新增接口
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody CompetitionParam competitionParam) {
        this.competitionService.add(competitionParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody CompetitionParam competitionParam) {
        this.competitionService.update(competitionParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody CompetitionParam competitionParam) {
        this.competitionService.delete(competitionParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody CompetitionParam competitionParam) {
        Competition detail = this.competitionService.getById(competitionParam.getId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody CompetitionParam competitionParam) {
        return this.competitionService.findPageBySpec(competitionParam);
    }

}


