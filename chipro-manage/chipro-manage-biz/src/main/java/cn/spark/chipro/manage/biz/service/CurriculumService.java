package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Curriculum;
import cn.spark.chipro.manage.api.model.params.CurriculumParam;
import cn.spark.chipro.manage.api.model.result.CurriculumResult;
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
public interface CurriculumService extends IService<Curriculum> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void add(CurriculumParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(CurriculumParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(CurriculumParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    CurriculumResult findBySpec(CurriculumParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<CurriculumResult> findListBySpec(CurriculumParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(CurriculumParam param);

}
