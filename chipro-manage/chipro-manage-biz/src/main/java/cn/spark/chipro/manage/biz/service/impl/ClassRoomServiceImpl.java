package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.ClassRoom;
import cn.spark.chipro.manage.biz.mapper.ClassRoomMapper;
import cn.spark.chipro.manage.api.model.params.ClassRoomParam;
import cn.spark.chipro.manage.api.model.result.ClassRoomResult;
import  cn.spark.chipro.manage.biz.service.ClassRoomService;
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
 * 课室 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class ClassRoomServiceImpl extends ServiceImpl<ClassRoomMapper, ClassRoom> implements ClassRoomService {

    @Override
    public void add(ClassRoomParam param){
        ClassRoom entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ClassRoomParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ClassRoomParam param){
        ClassRoom oldEntity = getOldEntity(param);
        ClassRoom newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ClassRoomResult findBySpec(ClassRoomParam param){
        return null;
    }

    @Override
    public List<ClassRoomResult> findListBySpec(ClassRoomParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(ClassRoomParam param){
        Page pageContext=getPageContext();
        QueryWrapper<ClassRoom>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(ClassRoomParam param){
        return param.getClassId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private ClassRoom getOldEntity(ClassRoomParam param) {
        return this.getById(getKey(param));
    }

    private ClassRoom getEntity(ClassRoomParam param) {
        ClassRoom entity = new ClassRoom();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
