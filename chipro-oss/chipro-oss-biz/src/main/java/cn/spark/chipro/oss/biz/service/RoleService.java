package cn.spark.chipro.oss.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.oss.biz.entity.Role;
import cn.spark.chipro.oss.biz.model.params.RoleParam;
import cn.spark.chipro.oss.biz.model.result.RoleResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
public interface RoleService extends IService<Role> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void add(RoleParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void delete(RoleParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    void update(RoleParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    RoleResult findBySpec(RoleParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    List<RoleResult> findListBySpec(RoleParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-31
     */
    PageInfo findPageBySpec(RoleParam param);

}
