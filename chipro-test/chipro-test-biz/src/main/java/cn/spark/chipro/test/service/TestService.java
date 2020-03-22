package cn.spark.chipro.test.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.test.entity.Test;
import cn.spark.chipro.test.model.params.TestParam;
import cn.spark.chipro.test.model.result.TestResult;
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
public interface TestService extends IService<Test> {

    /**
     * 测试分布式事务
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void dt();


    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void add(TestParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void delete(TestParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    void update(TestParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    TestResult findBySpec(TestParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    List<TestResult> findListBySpec(TestParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-01-27
     */
    PageInfo findPageBySpec(TestParam param);

}
