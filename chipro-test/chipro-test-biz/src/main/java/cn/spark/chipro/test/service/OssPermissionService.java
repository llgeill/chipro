package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OssPermission;
import cn.spark.chipro.test.model.params.OssPermissionParam;
import cn.spark.chipro.test.model.result.OssPermissionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OssPermissionService extends IService<OssPermission> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OssPermissionParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OssPermissionParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OssPermissionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OssPermissionResult findBySpec(OssPermissionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OssPermissionResult> findListBySpec(OssPermissionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OssPermissionParam param);

}
