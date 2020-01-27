package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OssRolePermission;
import cn.spark.chipro.test.model.params.OssRolePermissionParam;
import cn.spark.chipro.test.model.result.OssRolePermissionResult;
import cn.spark.chipro.test.service.OssRolePermissionService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 角色权限关系表控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/ossRolePermission")
public class OssRolePermissionController extends BaseController {

    private String PREFIX = "/ossRolePermission";

    @Autowired
    private OssRolePermissionService ossRolePermissionService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OssRolePermissionParam ossRolePermissionParam) {
        this.ossRolePermissionService.add(ossRolePermissionParam);
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
    public Result editItem(OssRolePermissionParam ossRolePermissionParam) {
        this.ossRolePermissionService.update(ossRolePermissionParam);
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
    public Result delete(OssRolePermissionParam ossRolePermissionParam) {
        this.ossRolePermissionService.delete(ossRolePermissionParam);
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
    public Result detail(OssRolePermissionParam ossRolePermissionParam) {
        OssRolePermission detail = this.ossRolePermissionService.getById(ossRolePermissionParam.getRoleAndPermissionId());
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
    public PageInfo list(OssRolePermissionParam ossRolePermissionParam) {
        return this.ossRolePermissionService.findPageBySpec(ossRolePermissionParam);
    }

}


