package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.util.UserContext;
import cn.spark.chipro.manage.biz.common.util.ManageUtil;
import cn.spark.chipro.manage.biz.entity.ClassRoom;
import cn.spark.chipro.manage.biz.entity.ClassUser;
import cn.spark.chipro.manage.biz.mapper.ClassRoomMapper;
import cn.spark.chipro.manage.api.model.params.ClassRoomParam;
import cn.spark.chipro.manage.api.model.result.ClassRoomResult;
import  cn.spark.chipro.manage.biz.service.ClassRoomService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.manage.biz.service.ClassUserService;
import cn.spark.chipro.oss.api.feign.UserFeignService;
import cn.spark.chipro.oss.api.model.params.UserParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ClassUserService classUserService;

    @Override
    @Transactional
    public ClassRoom add(ClassRoomParam param){
        //查询是否有重复学校+课室
        QueryWrapper<ClassRoom> classRoomQueryWrapper = new QueryWrapper<>();
        classRoomQueryWrapper.eq("SCHOOL_ID",param.getSchoolId()).eq("NAME",param.getName());
        ClassRoom classRoom = this.getOne(classRoomQueryWrapper);
        if(classRoom!=null){
            throw new CoreException(0,"重复的课室名称！");
        }
        //添加课室
        ClassRoom entity = getEntity(param);
        entity.setCode(ManageUtil.unRepeatSixCode());
        this.save(entity);
        //添加老师课室关系
        ClassUser classUser = new ClassUser();
        classUser.setClassRoomId(entity.getClassId());
        classUser.setUserId((String)UserContext.getUserInfo().get("userId"));
        this.classUserService.save(classUser);
        return entity;
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
