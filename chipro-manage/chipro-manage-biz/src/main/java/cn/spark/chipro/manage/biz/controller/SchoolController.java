package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.core.log.aop.WebLog;
import cn.spark.chipro.manage.api.model.validated.InsertValidated;
import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.api.model.params.SchoolParam;
import cn.spark.chipro.manage.biz.service.SchoolService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import cn.spark.chipro.oss.api.feign.UserFeignService;
import cn.spark.chipro.oss.api.model.params.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
    @WebLog(type = "insert",description = "学校信息申请并返回学校管理账号")
    public Result addItem(@RequestBody @Validated(InsertValidated.class) SchoolParam schoolParam) {
        UserParam userParam = this.schoolService.add(schoolParam);
        return Result.success(userParam);
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody SchoolParam schoolParam) {
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
    public Result delete(@RequestBody SchoolParam schoolParam) {
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
    public Result detail(@RequestBody SchoolParam schoolParam) {
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
    public PageInfo list(@RequestBody SchoolParam schoolParam) {
        return this.schoolService.findPageBySpec(schoolParam);
    }

}


