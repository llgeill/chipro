package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Resource;
import cn.spark.chipro.manage.api.model.params.ResourceParam;
import cn.spark.chipro.manage.api.model.result.ResourceResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void add(ResourceParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(ResourceParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(ResourceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    ResourceResult findBySpec(ResourceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<ResourceResult> findListBySpec(ResourceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(ResourceParam param);

}
