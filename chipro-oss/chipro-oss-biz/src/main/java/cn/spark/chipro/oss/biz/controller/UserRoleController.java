package cn.spark.chipro.oss.biz.controller;

import cn.spark.chipro.oss.biz.entity.UserRole;
import cn.spark.chipro.oss.api.model.params.UserRoleParam;
import cn.spark.chipro.oss.biz.service.UserRoleService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户角色关系表控制器
 *
 * @author 李利光
 * @Date 2020-01-31 13:26:51
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    private String PREFIX = "/userRole";

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(UserRoleParam userRoleParam) {
        this.userRoleService.add(userRoleParam);
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
    public Result editItem(UserRoleParam userRoleParam) {
        this.userRoleService.update(userRoleParam);
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
    public Result delete(UserRoleParam userRoleParam) {
        this.userRoleService.delete(userRoleParam);
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
    public Result detail(UserRoleParam userRoleParam) {
        UserRole detail = this.userRoleService.getById(userRoleParam.getUserAndRoleId());
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
    public PageInfo list(UserRoleParam userRoleParam) {
        return this.userRoleService.findPageBySpec(userRoleParam);
    }

}


