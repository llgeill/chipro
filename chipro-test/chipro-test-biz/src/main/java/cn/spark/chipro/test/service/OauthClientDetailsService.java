package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.OauthClientDetails;
import cn.spark.chipro.test.model.params.OauthClientDetailsParam;
import cn.spark.chipro.test.model.result.OauthClientDetailsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
public interface OauthClientDetailsService extends IService<OauthClientDetails> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(OauthClientDetailsParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(OauthClientDetailsParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(OauthClientDetailsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    OauthClientDetailsResult findBySpec(OauthClientDetailsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<OauthClientDetailsResult> findListBySpec(OauthClientDetailsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(OauthClientDetailsParam param);

}
