package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Resource;
import cn.spark.chipro.manage.api.model.params.ResourceParam;
import cn.spark.chipro.manage.biz.service.ResourceService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 资源控制器
 *
 * @author 李利光
 * @Date 2020-02-05 22:13:28
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private String PREFIX = "/resource";

    @Autowired
    private ResourceService resourceService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(ResourceParam resourceParam) {
        this.resourceService.add(resourceParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(ResourceParam resourceParam) {
        this.resourceService.update(resourceParam);
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
    public Result delete(ResourceParam resourceParam) {
        this.resourceService.delete(resourceParam);
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
    public Result detail(ResourceParam resourceParam) {
        Resource detail = this.resourceService.getById(resourceParam.getResourceId());
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
    public PageInfo list(ResourceParam resourceParam) {
        return this.resourceService.findPageBySpec(resourceParam);
    }

}


