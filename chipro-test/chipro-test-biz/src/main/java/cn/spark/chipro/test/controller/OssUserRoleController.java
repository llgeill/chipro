package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OssUserRole;
import cn.spark.chipro.test.model.params.OssUserRoleParam;
import cn.spark.chipro.test.model.result.OssUserRoleResult;
import cn.spark.chipro.test.service.OssUserRoleService;
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
 * 用户角色关系表控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/ossUserRole")
public class OssUserRoleController extends BaseController {

    private String PREFIX = "/ossUserRole";

    @Autowired
    private OssUserRoleService ossUserRoleService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OssUserRoleParam ossUserRoleParam) {
        this.ossUserRoleService.add(ossUserRoleParam);
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
    public Result editItem(OssUserRoleParam ossUserRoleParam) {
        this.ossUserRoleService.update(ossUserRoleParam);
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
    public Result delete(OssUserRoleParam ossUserRoleParam) {
        this.ossUserRoleService.delete(ossUserRoleParam);
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
    public Result detail(OssUserRoleParam ossUserRoleParam) {
        OssUserRole detail = this.ossUserRoleService.getById(ossUserRoleParam.getUserAndRoleId());
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
    public PageInfo list(OssUserRoleParam ossUserRoleParam) {
        return this.ossUserRoleService.findPageBySpec(ossUserRoleParam);
    }

}


