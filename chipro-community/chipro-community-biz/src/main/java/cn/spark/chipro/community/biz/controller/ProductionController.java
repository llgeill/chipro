package cn.spark.chipro.community.biz.controller;

import cn.spark.chipro.community.api.model.params.ProductUser;
import cn.spark.chipro.community.api.model.validated.InsertValidated;
import cn.spark.chipro.community.biz.entity.Production;
import cn.spark.chipro.community.api.model.params.ProductionParam;
import cn.spark.chipro.community.biz.service.ProductionService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 作品控制器
 *
 * @author 李利光
 * @Date 2020-02-05 23:30:16
 */
@Controller
@RequestMapping("/production")
public class ProductionController extends BaseController {

    private String PREFIX = "/production";

    @Autowired
    private ProductionService productionService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody @Validated(InsertValidated.class) ProductionParam productionParam) {
        this.productionService.add(productionParam);
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
    public Result editItem(@RequestBody ProductionParam productionParam) {
        this.productionService.update(productionParam);
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
    public Result delete(@RequestBody ProductionParam productionParam) {
        this.productionService.delete(productionParam);
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
    public Result detail(@RequestBody ProductionParam productionParam) {
        Production detail = this.productionService.getById(productionParam.getProductionId());
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
    public PageInfo list(@RequestBody ProductionParam productionParam) {
        return this.productionService.findPageBySpec(productionParam);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @ResponseBody
    @RequestMapping("/productions")
    public List<Production> productions(@RequestBody String userIds) {
        return this.productionService.findListByStudentId(userIds);
    }

    /**
     * 作品点赞
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @ResponseBody
    @RequestMapping("/giveLike")
    public Result giveLike(@RequestBody ProductUser productUser) {
        this.productionService.giveLike(productUser);
        return Result.success();
    }


    /**
     * 作品评论
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @ResponseBody
    @RequestMapping("/comment")
    public Result fabulousProduct(@RequestBody ProductUser productUser) {
        this.productionService.comment(productUser);
        return Result.success();
    }

    /**
     * 获取评论
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @ResponseBody
    @RequestMapping("/getComment")
    public Result getComment(@RequestBody ProductUser productUser) {
        List comment = this.productionService.getComment(productUser);
        return Result.success(comment);
    }




}


