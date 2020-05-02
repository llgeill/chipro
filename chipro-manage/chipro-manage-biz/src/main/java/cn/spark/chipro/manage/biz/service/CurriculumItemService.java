package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.CurriculumItem;
import cn.spark.chipro.manage.api.model.params.CurriculumItemParam;
import cn.spark.chipro.manage.api.model.result.CurriculumItemResult;
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
public interface CurriculumItemService extends IService<CurriculumItem> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void add(CurriculumItemParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(CurriculumItemParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(CurriculumItemParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    CurriculumItemResult findBySpec(CurriculumItemParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<CurriculumItemResult> findListBySpec(CurriculumItemParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(CurriculumItemParam param);

}
