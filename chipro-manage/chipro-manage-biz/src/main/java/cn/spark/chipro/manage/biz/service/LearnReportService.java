package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.api.model.params.LearnReportParam;
import cn.spark.chipro.manage.api.model.result.LearnReportResult;
import cn.spark.chipro.manage.biz.entity.LearnReport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学情报告 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-04-12
 */
public interface LearnReportService extends IService<LearnReport> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    void add(LearnReportParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    void delete(LearnReportParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    void update(LearnReportParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    LearnReportResult findBySpec(LearnReportParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    List<LearnReportResult> findListBySpec(LearnReportParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-04-12
     */
    PageInfo findPageBySpec(LearnReportParam param);

}
