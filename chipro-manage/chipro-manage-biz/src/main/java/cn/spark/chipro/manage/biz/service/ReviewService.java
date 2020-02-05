package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Review;
import cn.spark.chipro.manage.api.model.params.ReviewParam;
import cn.spark.chipro.manage.api.model.result.ReviewResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 作品点评 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface ReviewService extends IService<Review> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void add(ReviewParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(ReviewParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(ReviewParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    ReviewResult findBySpec(ReviewParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<ReviewResult> findListBySpec(ReviewParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(ReviewParam param);

}
