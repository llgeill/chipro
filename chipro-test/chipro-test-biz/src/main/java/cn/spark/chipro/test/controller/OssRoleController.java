package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OssRole;
import cn.spark.chipro.test.model.params.OssRoleParam;
import cn.spark.chipro.test.model.result.OssRoleResult;
import cn.spark.chipro.test.service.OssRoleService;
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
 * 角色表控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/ossRole")
public class OssRoleController extends BaseController {

    private String PREFIX = "/ossRole";

    @Autowired
    private OssRoleService ossRoleService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OssRoleParam ossRoleParam) {
        this.ossRoleService.add(ossRoleParam);
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
    public Result editItem(OssRoleParam ossRoleParam) {
        this.ossRoleService.update(ossRoleParam);
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
    public Result delete(OssRoleParam ossRoleParam) {
        this.ossRoleService.delete(ossRoleParam);
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
    public Result detail(OssRoleParam ossRoleParam) {
        OssRole detail = this.ossRoleService.getById(ossRoleParam.getRoleId());
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
    public PageInfo list(OssRoleParam ossRoleParam) {
        return this.ossRoleService.findPageBySpec(ossRoleParam);
    }

}


