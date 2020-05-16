package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.manage.biz.entity.Feedback;
import cn.spark.chipro.manage.api.model.params.FeedbackParam;
import cn.spark.chipro.manage.biz.service.FeedbackService;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 反馈控制器
 *
 * @author 李利光
 * @Date 2020-05-09 14:46:13
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {


    @Autowired
    private FeedbackService feedbackService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(@RequestBody FeedbackParam feedbackParam) {
        this.feedbackService.add(feedbackParam);
        return Result.success();
    }

    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody FeedbackParam feedbackParam) {
        this.feedbackService.update(feedbackParam);
        return Result.success();
    }

    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody FeedbackParam feedbackParam) {
        this.feedbackService.delete(feedbackParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestBody FeedbackParam feedbackParam) {
        Feedback detail = this.feedbackService.getById(feedbackParam.getFeedbackId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-05-09
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestBody FeedbackParam feedbackParam) {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(feedbackParam.getUserId())){
            queryWrapper.eq("CREATE_USER",feedbackParam.getUserId());
        }
        return Result.success(this.feedbackService.list(queryWrapper));
    }

}


