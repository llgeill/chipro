package cn.spark.chipro.oss.biz.controller;

import cn.spark.chipro.core.log.aop.WebLog;
import cn.spark.chipro.oss.api.model.validated.*;
import cn.spark.chipro.oss.biz.entity.User;
import cn.spark.chipro.oss.api.model.params.UserParam;
import cn.spark.chipro.oss.biz.service.UserService;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * 用户表控制器
 *
 * @author 李利光
 * @Date 2020-01-31 13:26:51
 */
@Controller
@Validated
@RequestMapping("/user")
public class UserController extends BaseController {

    private String PREFIX = "/user";

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/addItem")
    @ResponseBody
    @WebLog(type = "insert",description = "用户注册")
    public Result addItem(@Validated(InsertValidated.class) @RequestBody UserParam userParam) {
        return Result.success(this.userService.register(userParam));
    }

    /**
     * 修改密码
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/forgetPass")
    @ResponseBody
    @WebLog(type = "update",description = "忘记密码")
    public Result forgetPass(@Validated(UpdateValidated.class) @RequestBody UserParam userParam) {
        this.userService.forgetPass(userParam);
        return Result.success();
    }

    /**
     * 批量注册学生账号
     *
     * @author 李利光
     * @Date 2020-02-08
     */
    @RequestMapping("/batchRegisterStuAccount")
    @ResponseBody
    @WebLog(type = "insert",description = "批量注册学生账号")
    public Result forgetPass(@RequestParam("file")  MultipartFile multipartFile,String classRoomId) {
        List<User> users = this.userService.batchRegisterStuAccount(multipartFile,classRoomId);
        return Result.success(users);
    }

    /**
     * 获取用户别名
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/getUserNameById")
    @ResponseBody
    @WebLog(type = "select",description = "通过id获取用户名称")
    public String getUserNameById(@NotEmpty @RequestBody String userId) {
        return this.userService.getUserNameById(userId);
    }



    /**
     * 编辑接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public Result editItem(@RequestBody UserParam userParam) {
        this.userService.update(userParam);
        return Result.success();
    }


    /**
     * 删除接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody UserParam userParam) {
        this.userService.delete(userParam);
        return Result.success();
    }

    /**
     * 查看详情接口
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(UserParam userParam) {
        User detail = this.userService.getById(userParam.getUserId());
        return Result.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo list(@RequestBody UserParam userParam) {
        return this.userService.findPageBySpec(userParam);
    }

}


