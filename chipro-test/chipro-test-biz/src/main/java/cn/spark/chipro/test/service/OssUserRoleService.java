package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OssUserRole;
import cn.spark.chipro.test.model.params.OssUserRoleParam;
import cn.spark.chipro.test.model.result.OssUserRoleResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OssUserRoleService extends IService<OssUserRole> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OssUserRoleParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OssUserRoleParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OssUserRoleParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OssUserRoleResult findBySpec(OssUserRoleParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OssUserRoleResult> findListBySpec(OssUserRoleParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OssUserRoleParam param);

}
