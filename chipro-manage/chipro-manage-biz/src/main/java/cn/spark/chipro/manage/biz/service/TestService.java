package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.manage.biz.entity.Test;
import cn.spark.chipro.manage.api.model.params.TestParam;
import cn.spark.chipro.manage.api.model.result.TestResult;
import cn.spark.chipro.manage.biz.vo.TestVO;
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
public interface TestService extends IService<Test> {

    /**
     * 新增
     *
     * @author LCF
     * @Date 2020-04-11
     */
    Result add(TestParam param);

    /**
     * 删除
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void delete(TestParam param);

    /**
     * 更新
     *
     * @author LCF
     * @Date 2020-04-11
     */
    void update(TestParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    TestResult findBySpec(TestParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    List<TestResult> findListBySpec(TestParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author LCF
     * @Date 2020-04-11
     */
    PageInfo findPageBySpec(TestParam param);


    public TestVO createRandomTest(String classify, Integer questionQuantity);

}
