package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Curriculum;
import cn.spark.chipro.manage.api.model.params.CurriculumParam;
import cn.spark.chipro.manage.api.model.result.CurriculumResult;
import cn.spark.chipro.manage.biz.service.CurriculumService;
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
@RequestMapping("/curriculum")
public class CurriculumController extends BaseController {


    @Autowired
    private CurriculumService curriculumService;

    /**
     * 新增接口
     *
     * @author LCF
     * @Date 2020-04-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody CurriculumParam curriculumParam) {
        this.curriculumService.add(curriculumParam);
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
    public Result editItem(@RequestBody CurriculumParam curriculumParam) {
        this.curriculumService.update(curriculumParam);
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
    public Result delete(@RequestBody CurriculumParam curriculumParam) {
        this.curriculumService.delete(curriculumParam);
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
    public Result detail(@RequestBody CurriculumParam curriculumParam) {
        Curriculum detail = this.curriculumService.getById(curriculumParam.getId());
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
    public PageInfo list(@RequestBody CurriculumParam curriculumParam) {
        return this.curriculumService.findPageBySpec(curriculumParam);
    }

}


