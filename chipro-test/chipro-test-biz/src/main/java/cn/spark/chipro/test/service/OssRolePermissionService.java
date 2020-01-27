package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OssRolePermission;
import cn.spark.chipro.test.model.params.OssRolePermissionParam;
import cn.spark.chipro.test.model.result.OssRolePermissionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OssRolePermissionService extends IService<OssRolePermission> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OssRolePermissionParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OssRolePermissionParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OssRolePermissionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OssRolePermissionResult findBySpec(OssRolePermissionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OssRolePermissionResult> findListBySpec(OssRolePermissionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OssRolePermissionParam param);

}
