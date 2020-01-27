package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OauthClientDetails;
import cn.spark.chipro.test.model.params.OauthClientDetailsParam;
import cn.spark.chipro.test.model.result.OauthClientDetailsResult;
import cn.spark.chipro.test.service.OauthClientDetailsService;
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
 * 控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/oauthClientDetails")
public class OauthClientDetailsController extends BaseController {

    private String PREFIX = "/oauthClientDetails";

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OauthClientDetailsParam oauthClientDetailsParam) {
        this.oauthClientDetailsService.add(oauthClientDetailsParam);
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
    public Result editItem(OauthClientDetailsParam oauthClientDetailsParam) {
        this.oauthClientDetailsService.update(oauthClientDetailsParam);
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
    public Result delete(OauthClientDetailsParam oauthClientDetailsParam) {
        this.oauthClientDetailsService.delete(oauthClientDetailsParam);
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
    public Result detail(OauthClientDetailsParam oauthClientDetailsParam) {
        OauthClientDetails detail = this.oauthClientDetailsService.getById(oauthClientDetailsParam.getClientId());
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
    public PageInfo list(OauthClientDetailsParam oauthClientDetailsParam) {
        return this.oauthClientDetailsService.findPageBySpec(oauthClientDetailsParam);
    }

}


