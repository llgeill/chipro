package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.core.log.aop.WebLog;
import cn.spark.chipro.manage.api.model.validated.InsertValidated;
import cn.spark.chipro.manage.biz.entity.ClassUser;
import cn.spark.chipro.manage.api.model.params.ClassUserParam;
import cn.spark.chipro.manage.biz.service.ClassUserService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 课室老师控制器
 *
 * @author 李利光
 * @Date 2020-02-07 18:17:58
 */
@Controller
@RequestMapping("/classUser")
public class ClassUserController extends BaseController {


    @Autowired
    private ClassUserService classUserService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody ClassUserParam classUserParam) {
        this.classUserService.add(classUserParam);
        return Result.success();
    }

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    @RequestMapping("/batchAdd")
    @ResponseBody
    @WebLog(type = "insert",description = "批量新增学生课室关系")
    public Result addItem(@RequestBody @Validated(InsertValidated.class) List<ClassUserParam> classUserParamList) {
        this.classUserService.batchAdd(classUserParamList);
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
    public Result editItem(@RequestBody ClassUserParam classUserParam) {
        this.classUserService.update(classUserParam);
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
    public Result delete(@RequestBody ClassUserParam classUserParam) {
        this.classUserService.delete(classUserParam);
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
    public Result detail(@RequestBody ClassUserParam classUserParam) {
        ClassUser detail = this.classUserService.getById(classUserParam.getClassUserId());
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
    public PageInfo list(@RequestBody ClassUserParam classUserParam) {
        return this.classUserService.findPageBySpec(classUserParam);
    }

}


