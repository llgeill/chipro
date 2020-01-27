package cn.spark.chipro.test.controller;

import cn.spark.chipro.test.entity.OssUser;
import cn.spark.chipro.test.model.params.OssUserParam;
import cn.spark.chipro.test.model.result.OssUserResult;
import cn.spark.chipro.test.service.OssUserService;
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
 * 用户表控制器
 *
 * @author 李利光
 * @Date 2020-01-27 13:48:40
 */
@Controller
@RequestMapping("/ossUser")
public class OssUserController extends BaseController {

    private String PREFIX = "/ossUser";

    @Autowired
    private OssUserService ossUserService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(OssUserParam ossUserParam) {
        this.ossUserService.add(ossUserParam);
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
    public Result editItem(OssUserParam ossUserParam) {
        this.ossUserService.update(ossUserParam);
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
    public Result delete(OssUserParam ossUserParam) {
        this.ossUserService.delete(ossUserParam);
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
    public Result detail(OssUserParam ossUserParam) {
        OssUser detail = this.ossUserService.getById(ossUserParam.getUserId());
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
    public PageInfo list(OssUserParam ossUserParam) {
        return this.ossUserService.findPageBySpec(ossUserParam);
    }

}


