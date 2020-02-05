package cn.spark.chipro.manage.biz.service;

import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.manage.biz.entity.Task;
import cn.spark.chipro.manage.api.model.params.TaskParam;
import cn.spark.chipro.manage.api.model.result.TaskResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 任务 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface TaskService extends IService<Task> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void add(TaskParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(TaskParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(TaskParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    TaskResult findBySpec(TaskParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<TaskResult> findListBySpec(TaskParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(TaskParam param);

}
