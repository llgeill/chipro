package cn.spark.chipro.oss.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.oss.biz.entity.Permission;
import cn.spark.chipro.oss.biz.model.params.PermissionParam;
import cn.spark.chipro.oss.biz.model.result.PermissionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void add(PermissionParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void delete(PermissionParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void update(PermissionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PermissionResult findBySpec(PermissionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    List<PermissionResult> findListBySpec(PermissionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PageInfo findPageBySpec(PermissionParam param);

}
