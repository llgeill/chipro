package cn.spark.chipro.oss.biz.controller;

import cn.spark.chipro.oss.biz.entity.Permission;
import cn.spark.chipro.oss.api.model.params.PermissionParam;
import cn.spark.chipro.oss.biz.service.PermissionService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 权限表控制器
 *
 * @author 李利光
 * @Date 2020-01-31 13:26:51
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private String PREFIX = "/permission";

    @Autowired
    private PermissionService permissionService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody PermissionParam permissionParam) {
        this.permissionService.add(permissionParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody PermissionParam permissionParam) {
        this.permissionService.update(permissionParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody PermissionParam permissionParam) {
        this.permissionService.delete(permissionParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody PermissionParam permissionParam) {
        Permission detail = this.permissionService.getById(permissionParam.getPermissionId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody PermissionParam permissionParam) {
        return this.permissionService.findPageBySpec(permissionParam);
    }

}


