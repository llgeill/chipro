package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.api.model.params.SchoolParam;
import cn.spark.chipro.manage.biz.service.SchoolService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 学校控制器
 *
 * @author 李利光
 * @Date 2020-02-05 22:13:28
 */
@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {

    private String PREFIX = "/school";

    @Autowired
    private SchoolService schoolService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(SchoolParam schoolParam) {
        this.schoolService.add(schoolParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(SchoolParam schoolParam) {
        this.schoolService.update(schoolParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(SchoolParam schoolParam) {
        this.schoolService.delete(schoolParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(SchoolParam schoolParam) {
        School detail = this.schoolService.getById(schoolParam.getSchoolId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(SchoolParam schoolParam) {
        return this.schoolService.findPageBySpec(schoolParam);
    }

}


