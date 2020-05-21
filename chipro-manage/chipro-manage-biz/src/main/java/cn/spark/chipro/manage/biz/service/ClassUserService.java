package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.ClassUser;
import cn.spark.chipro.manage.api.model.params.ClassUserParam;
import cn.spark.chipro.manage.api.model.result.ClassUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课室老师 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
public interface ClassUserService extends IService<ClassUser> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void add(ClassUserParam param);

    /**
     * 根据classUserParamList批量新增
     * @param classUserParamList
     */
    void batchAdd(List<ClassUserParam> classUserParamList);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void delete(ClassUserParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    void update(ClassUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    ClassUserResult findBySpec(ClassUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    List<ClassUserResult> findListBySpec(ClassUserParam classRoomId);

    /**
     * 通过课室ID获取所有的学生
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    List<ClassUser> findStudentByCLassID(ClassUserParam classUserParam);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-07
     */
    PageInfo findPageBySpec(ClassUserParam param);

}
