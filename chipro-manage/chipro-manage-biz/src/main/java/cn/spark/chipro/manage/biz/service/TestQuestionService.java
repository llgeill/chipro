package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.TestQuestion;
import cn.spark.chipro.manage.api.model.params.TestQuestionParam;
import cn.spark.chipro.manage.api.model.result.TestQuestionResult;
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
public interface TestQuestionService extends IService<TestQuestion> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void add(TestQuestionParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(TestQuestionParam param);

    public void deleteByQuestionId(TestQuestionParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(TestQuestionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    TestQuestionResult findBySpec(TestQuestionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<TestQuestionResult> findListBySpec(TestQuestionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(TestQuestionParam param);

}
