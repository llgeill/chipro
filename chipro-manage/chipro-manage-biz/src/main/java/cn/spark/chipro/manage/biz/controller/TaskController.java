package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.Task;
import cn.spark.chipro.manage.api.model.params.TaskParam;
import cn.spark.chipro.manage.biz.service.TaskService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 任务控制器
 *
 * @author 李利光
 * @Date 2020-02-05 22:13:28
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    private String PREFIX = "/task";

    @Autowired
    private TaskService taskService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(TaskParam taskParam) {
        this.taskService.add(taskParam);
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
    public Result editItem(TaskParam taskParam) {
        this.taskService.update(taskParam);
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
    public Result delete(TaskParam taskParam) {
        this.taskService.delete(taskParam);
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
    public Result detail(TaskParam taskParam) {
        Task detail = this.taskService.getById(taskParam.getTaskId());
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
    public PageInfo list(TaskParam taskParam) {
        return this.taskService.findPageBySpec(taskParam);
    }

}


