package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.community.api.model.feign.CommunityFeignService;
import cn.spark.chipro.community.api.model.result.ProductionResult;
import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.util.UserContext;
import cn.spark.chipro.manage.biz.common.util.ManageUtil;
import cn.spark.chipro.manage.biz.entity.ClassRoom;
import cn.spark.chipro.manage.biz.entity.ClassUser;
import cn.spark.chipro.manage.biz.entity.Review;
import cn.spark.chipro.manage.biz.mapper.ClassRoomMapper;
import cn.spark.chipro.manage.api.model.params.ClassRoomParam;
import cn.spark.chipro.manage.api.model.result.ClassRoomResult;
import  cn.spark.chipro.manage.biz.service.ClassRoomService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.manage.biz.service.ClassUserService;
import cn.spark.chipro.manage.biz.service.ReviewService;
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
import java.util.stream.Collectors;

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

    @Autowired
    private CommunityFeignService communityFeginService;

    @Autowired
    private ReviewService reviewService;


    /**
     * 创建班级
     * @param param
     * @return
     */
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

    /**
     * 分页查询所有课室信息
     * @param param
     * @return
     */
    @Override
    public PageInfo findPageBySpec(ClassRoomParam param){
        Page pageContext=getPageContext();
        QueryWrapper<ClassRoom>objectQueryWrapper=new QueryWrapper<>();
        //关联查询班级下学生人数已经学生作业平均分
        IPage page=this.page(pageContext,objectQueryWrapper);
        //查询所有学生
        List<ClassRoom> classRoomList = page.getRecords();
        classRoomList.forEach(classRoom -> {
            //获取所有学生
            List<ClassUser> student = classUserService.findStudentByCLassID(classRoom.getClassId());
            classRoom.setPersonCount(String.valueOf(student.size()));
            List<String> userIds = student.stream().map(ClassUser::getUserId).collect(Collectors.toList());
            List<Review> reviewList = reviewService.list(new QueryWrapper<Review>().in("USER_ID", userIds));
            //获取平均分数
            double score = reviewList.stream().mapToInt(s -> Integer.parseInt(s.getScope())).average().getAsDouble();
            classRoom.setScore(String.valueOf(score));
        });
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
