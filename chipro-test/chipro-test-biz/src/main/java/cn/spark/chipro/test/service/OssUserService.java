package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OssUser;
import cn.spark.chipro.test.model.params.OssUserParam;
import cn.spark.chipro.test.model.result.OssUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OssUserService extends IService<OssUser> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OssUserParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OssUserParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OssUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OssUserResult findBySpec(OssUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OssUserResult> findListBySpec(OssUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OssUserParam param);

}
