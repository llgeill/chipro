package cn.spark.chipro.oss.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.oss.api.model.params.UserRoleParam;
import cn.spark.chipro.oss.api.model.result.UserRoleResult;
import cn.spark.chipro.oss.biz.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void add(UserRoleParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void delete(UserRoleParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void update(UserRoleParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    UserRoleResult findBySpec(UserRoleParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    List<UserRoleResult> findListBySpec(UserRoleParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PageInfo findPageBySpec(UserRoleParam param);

}
