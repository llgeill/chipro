package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Review;
import cn.spark.chipro.manage.api.model.params.ReviewParam;
import cn.spark.chipro.manage.biz.service.ReviewService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 作品点评控制器
 *
 * @author 李利光
 * @Date 2020-02-05 22:13:28
 */
@Controller
@RequestMapping("/review")
public class ReviewController extends BaseController {

    private String PREFIX = "/review";

    @Autowired
    private ReviewService reviewService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(ReviewParam reviewParam) {
        this.reviewService.add(reviewParam);
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
    public Result editItem(ReviewParam reviewParam) {
        this.reviewService.update(reviewParam);
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
    public Result delete(ReviewParam reviewParam) {
        this.reviewService.delete(reviewParam);
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
    public Result detail(ReviewParam reviewParam) {
        Review detail = this.reviewService.getById(reviewParam.getReviewId());
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
    public PageInfo list(ReviewParam reviewParam) {
        return this.reviewService.findPageBySpec(reviewParam);
    }

}


