package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OssRole;
import cn.spark.chipro.test.model.params.OssRoleParam;
import cn.spark.chipro.test.model.result.OssRoleResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OssRoleService extends IService<OssRole> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OssRoleParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OssRoleParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OssRoleParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OssRoleResult findBySpec(OssRoleParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OssRoleResult> findListBySpec(OssRoleParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OssRoleParam param);

}
