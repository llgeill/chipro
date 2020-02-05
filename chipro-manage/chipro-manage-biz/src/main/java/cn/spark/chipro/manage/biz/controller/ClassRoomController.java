package cn.spark.chipro.manage.biz.controller;

import cn.spark.chipro.manage.biz.entity.ClassRoom;
import cn.spark.chipro.manage.api.model.params.ClassRoomParam;
import cn.spark.chipro.manage.biz.service.ClassRoomService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 课室控制器
 *
 * @author 李利光
 * @Date 2020-02-05 22:13:28
 */
@Controller
@RequestMapping("/classRoom")
public class ClassRoomController extends BaseController {

    private String PREFIX = "/classRoom";

    @Autowired
    private ClassRoomService classRoomService;

    /**
     * 新增接口
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(ClassRoomParam classRoomParam) {
        this.classRoomService.add(classRoomParam);
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
    public Result editItem(ClassRoomParam classRoomParam) {
        this.classRoomService.update(classRoomParam);
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
    public Result delete(ClassRoomParam classRoomParam) {
        this.classRoomService.delete(classRoomParam);
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
    public Result detail(ClassRoomParam classRoomParam) {
        ClassRoom detail = this.classRoomService.getById(classRoomParam.getClassId());
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
    public PageInfo list(ClassRoomParam classRoomParam) {
        return this.classRoomService.findPageBySpec(classRoomParam);
    }

}


