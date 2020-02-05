package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.ClassRoom;
import cn.spark.chipro.manage.api.model.params.ClassRoomParam;
import cn.spark.chipro.manage.api.model.result.ClassRoomResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课室 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface ClassRoomService extends IService<ClassRoom> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void add(ClassRoomParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(ClassRoomParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(ClassRoomParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    ClassRoomResult findBySpec(ClassRoomParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<ClassRoomResult> findListBySpec(ClassRoomParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(ClassRoomParam param);

}
