package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Competition;
import cn.spark.chipro.manage.api.model.params.CompetitionParam;
import cn.spark.chipro.manage.api.model.result.CompetitionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
public interface CompetitionService extends IService<Competition> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void add(CompetitionParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(CompetitionParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(CompetitionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    CompetitionResult findBySpec(CompetitionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<CompetitionResult> findListBySpec(CompetitionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(CompetitionParam param);

}
