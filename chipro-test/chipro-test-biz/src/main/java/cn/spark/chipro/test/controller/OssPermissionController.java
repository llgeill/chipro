package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OssPermission;
import cn.spark.chipro.test.model.params.OssPermissionParam;
import cn.spark.chipro.test.model.result.OssPermissionResult;
import cn.spark.chipro.test.service.OssPermissionService;
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
 * 权限表控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:39
 */
@Controller
@RequestMapping("/ossPermission")
public class OssPermissionController extends BaseController {

    private String PREFIX = "/ossPermission";

    @Autowired
    private OssPermissionService ossPermissionService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OssPermissionParam ossPermissionParam) {
        this.ossPermissionService.add(ossPermissionParam);
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
    public Result editItem(OssPermissionParam ossPermissionParam) {
        this.ossPermissionService.update(ossPermissionParam);
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
    public Result delete(OssPermissionParam ossPermissionParam) {
        this.ossPermissionService.delete(ossPermissionParam);
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
    public Result detail(OssPermissionParam ossPermissionParam) {
        OssPermission detail = this.ossPermissionService.getById(ossPermissionParam.getPermissionId());
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
    public PageInfo list(OssPermissionParam ossPermissionParam) {
        return this.ossPermissionService.findPageBySpec(ossPermissionParam);
    }

}


