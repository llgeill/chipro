package cn.spark.chipro.oss.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.oss.api.model.params.UserParam;
import cn.spark.chipro.oss.api.model.result.UserResult;
import cn.spark.chipro.oss.biz.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
public interface UserService extends IService<User> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void add(UserParam param);

    /**
     * 注册用户
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    User register(UserParam param);

    /**
     * 批量注册学生账号
      * @param multipartFile
     * @return
     */
    List<User> batchRegisterStuAccount(MultipartFile multipartFile,String classRoomId);

    /**
     * 忘记密码
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    User forgetPass(UserParam param);

        /**
         * 判断账号是否重复
         *
         * @author 李利光
         * @Date 2020-01-31
         */
    boolean repeatAccount(UserParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void delete(UserParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void update(UserParam param);

    /**
     * 重置密码
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void resetPass(UserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    UserResult findBySpec(UserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    List<UserResult> findListBySpec(UserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PageInfo findPageBySpec(UserParam param);

    /**
     * 通过用户id获取用户别名
     * @param userId
     */
    String getUserNameById(String userId);
}
