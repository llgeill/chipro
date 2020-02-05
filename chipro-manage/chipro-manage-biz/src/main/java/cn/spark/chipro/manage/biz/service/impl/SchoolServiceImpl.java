package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.biz.mapper.SchoolMapper;
import cn.spark.chipro.manage.api.model.params.SchoolParam;
import cn.spark.chipro.manage.api.model.result.SchoolResult;
import  cn.spark.chipro.manage.biz.service.SchoolService;
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
 * 学校 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Override
    public void add(SchoolParam param){
        School entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SchoolParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SchoolParam param){
        School oldEntity = getOldEntity(param);
        School newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SchoolResult findBySpec(SchoolParam param){
        return null;
    }

    @Override
    public List<SchoolResult> findListBySpec(SchoolParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(SchoolParam param){
        Page pageContext=getPageContext();
        QueryWrapper<School>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(SchoolParam param){
        return param.getSchoolId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private School getOldEntity(SchoolParam param) {
        return this.getById(getKey(param));
    }

    private School getEntity(SchoolParam param) {
        School entity = new School();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
