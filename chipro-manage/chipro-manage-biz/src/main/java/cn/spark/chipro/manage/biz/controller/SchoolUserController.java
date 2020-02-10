package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.SchoolUser;
import cn.spark.chipro.manage.api.model.params.SchoolUserParam;
import cn.spark.chipro.manage.biz.service.SchoolUserService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 学校校长控制器
 *
 * @author 李利光
 * @Date 2020-02-07 18:17:59
 */
@Controller
@RequestMapping("/schoolUser")
public class SchoolUserController extends BaseController {


    @Autowired
    private SchoolUserService schoolUserService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody SchoolUserParam schoolUserParam) {
        this.schoolUserService.add(schoolUserParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody SchoolUserParam schoolUserParam) {
        this.schoolUserService.update(schoolUserParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody SchoolUserParam schoolUserParam) {
        this.schoolUserService.delete(schoolUserParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody SchoolUserParam schoolUserParam) {
        SchoolUser detail = this.schoolUserService.getById(schoolUserParam.getSchoolUserId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody SchoolUserParam schoolUserParam) {
        return this.schoolUserService.findPageBySpec(schoolUserParam);
    }

}


