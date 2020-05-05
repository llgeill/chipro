package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.biz.entity.SchoolUser;
import cn.spark.chipro.manage.biz.mapper.SchoolUserMapper;
import cn.spark.chipro.manage.api.model.params.SchoolUserParam;
import cn.spark.chipro.manage.api.model.result.SchoolUserResult;
import cn.spark.chipro.manage.biz.service.SchoolService;
import  cn.spark.chipro.manage.biz.service.SchoolUserService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.oss.api.feign.UserFeignService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学校校长 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
@Service
public class SchoolUserServiceImpl extends ServiceImpl<SchoolUserMapper, SchoolUser> implements SchoolUserService {

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    SchoolService schoolService;

    @Override
    public void add(SchoolUserParam param){
        if(param!=null){
            if(StringUtil.isNotEmpty(param.getSchool())){
                QueryWrapper<School> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("NAME",param.getSchool())
                        .or()
                        .eq("CODE",param.getSchool());
                School one = schoolService.getOne(queryWrapper);
                if(one!=null){
                    param.setSchoolId(one.getSchoolId());
                }
            }
        }
        SchoolUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SchoolUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SchoolUserParam param){
        SchoolUser oldEntity = getOldEntity(param);
        SchoolUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SchoolUserResult findBySpec(SchoolUserParam param){
        return null;
    }

    @Override
    public List<SchoolUserResult> findListBySpec(SchoolUserParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(SchoolUserParam param){
        Page pageContext=getPageContext();
        QueryWrapper<SchoolUser>objectQueryWrapper=new QueryWrapper<>();
        if(param!=null){
            //remarks 0 则 未审核 1 审核
            if(StringUtil.isNotEmpty(param.getRemarks())){
                objectQueryWrapper.eq("REMARKS",param.getRemarks());
            }
        }
        IPage<SchoolUser> page=this.page(pageContext,objectQueryWrapper);
        List<SchoolUser> records = page.getRecords();
        records.forEach(s->{
            String name = userFeignService.getUserNameById(s.getUserId());
            if(StringUtil.isNotEmpty(name)){
                s.setUserName(name);
            }
        });
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(SchoolUserParam param){
        return param.getSchoolUserId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private SchoolUser getOldEntity(SchoolUserParam param) {
        return this.getById(getKey(param));
    }

    private SchoolUser getEntity(SchoolUserParam param) {
        SchoolUser entity = new SchoolUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
