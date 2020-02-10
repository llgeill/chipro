package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.SchoolUser;
import cn.spark.chipro.manage.api.model.params.SchoolUserParam;
import cn.spark.chipro.manage.api.model.result.SchoolUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学校校长 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
public interface SchoolUserService extends IService<SchoolUser> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void add(SchoolUserParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void delete(SchoolUserParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void update(SchoolUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    SchoolUserResult findBySpec(SchoolUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    List<SchoolUserResult> findListBySpec(SchoolUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    PageInfo findPageBySpec(SchoolUserParam param);

}
