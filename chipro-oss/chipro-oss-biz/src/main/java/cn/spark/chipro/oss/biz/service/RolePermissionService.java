package cn.spark.chipro.oss.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.oss.biz.entity.RolePermission;
import cn.spark.chipro.oss.biz.model.params.RolePermissionParam;
import cn.spark.chipro.oss.biz.model.result.RolePermissionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void add(RolePermissionParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void delete(RolePermissionParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void update(RolePermissionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    RolePermissionResult findBySpec(RolePermissionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    List<RolePermissionResult> findListBySpec(RolePermissionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PageInfo findPageBySpec(RolePermissionParam param);

}
