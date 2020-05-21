package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Feedback;
import cn.spark.chipro.manage.api.model.params.FeedbackParam;
import cn.spark.chipro.manage.api.model.result.FeedbackResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 反馈 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-05-09
 */
public interface FeedbackService extends IService<Feedback> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    void add(FeedbackParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    void delete(FeedbackParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    void update(FeedbackParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    FeedbackResult findBySpec(FeedbackParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    List<FeedbackResult> findListBySpec(FeedbackParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-05-09
     */
    PageInfo findPageBySpec(FeedbackParam param);

}
