package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Question;
import cn.spark.chipro.manage.api.model.params.QuestionParam;
import cn.spark.chipro.manage.api.model.result.QuestionResult;
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
public interface QuestionService extends IService<Question> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void add(QuestionParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(QuestionParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(QuestionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    QuestionResult findBySpec(QuestionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<QuestionResult> findListBySpec(QuestionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(QuestionParam param);

}
