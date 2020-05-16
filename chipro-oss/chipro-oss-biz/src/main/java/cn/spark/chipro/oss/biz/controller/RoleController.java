package cn.spark.chipro.oss.biz.controller;

import cn.spark.chipro.oss.biz.entity.Role;
import cn.spark.chipro.oss.api.model.params.RoleParam;
import cn.spark.chipro.oss.biz.service.RoleService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 角色表控制器
 *
 * @author 李利光
 * @Date 2020-01-31 13:26:51
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private String PREFIX = "/role";

    @Autowired
    private RoleService roleService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody RoleParam roleParam) {
        this.roleService.add(roleParam);
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
    public Result editItem(@RequestBody RoleParam roleParam) {
        this.roleService.update(roleParam);
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
    public Result delete(@RequestBody RoleParam roleParam) {
        this.roleService.delete(roleParam);
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
    public Result detail(@RequestBody RoleParam roleParam) {
        Role detail = this.roleService.getById(roleParam.getRoleId());
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
    public PageInfo list(@RequestBody RoleParam roleParam) {
        return this.roleService.findPageBySpec(roleParam);
    }

}


