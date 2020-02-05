package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Task;
import cn.spark.chipro.manage.biz.mapper.TaskMapper;
import cn.spark.chipro.manage.api.model.params.TaskParam;
import cn.spark.chipro.manage.api.model.result.TaskResult;
import  cn.spark.chipro.manage.biz.service.TaskService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 任务 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public void add(TaskParam param){
        Task entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TaskParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TaskParam param){
        Task oldEntity = getOldEntity(param);
        Task newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TaskResult findBySpec(TaskParam param){
        return null;
    }

    @Override
    public List<TaskResult> findListBySpec(TaskParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(TaskParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Task>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(TaskParam param){
        return param.getTaskId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Task getOldEntity(TaskParam param) {
        return this.getById(getKey(param));
    }

    private Task getEntity(TaskParam param) {
        Task entity = new Task();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
