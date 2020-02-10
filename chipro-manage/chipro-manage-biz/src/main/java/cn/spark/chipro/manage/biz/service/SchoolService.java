package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.api.model.params.SchoolParam;
import cn.spark.chipro.manage.api.model.result.SchoolResult;
import cn.spark.chipro.oss.api.model.params.UserParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学校 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface SchoolService extends IService<School> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    UserParam add(SchoolParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(SchoolParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(SchoolParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    SchoolResult findBySpec(SchoolParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<SchoolResult> findListBySpec(SchoolParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(SchoolParam param);

}
