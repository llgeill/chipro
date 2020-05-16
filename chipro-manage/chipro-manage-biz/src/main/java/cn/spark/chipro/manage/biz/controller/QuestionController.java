package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Question;
import cn.spark.chipro.manage.api.model.params.QuestionParam;
import cn.spark.chipro.manage.api.model.result.QuestionResult;
import cn.spark.chipro.manage.biz.service.QuestionService;
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
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {


    @Autowired
    private QuestionService questionService;

    /**
     * 新增接口
     *
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody QuestionParam questionParam) {
        this.questionService.add(questionParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody QuestionParam questionParam) {
        this.questionService.update(questionParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody QuestionParam questionParam) {
        this.questionService.delete(questionParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody QuestionParam questionParam) {
        Question detail = this.questionService.getById(questionParam.getId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody QuestionParam questionParam) {
        return this.questionService.findPageBySpec(questionParam);
    }

}


