package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OssUserRole;
import cn.spark.chipro.test.mapper.OssUserRoleMapper;
import cn.spark.chipro.test.model.params.OssUserRoleParam;
import cn.spark.chipro.test.model.result.OssUserRoleResult;
import  cn.spark.chipro.test.service.OssUserRoleService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Service
public class OssUserRoleServiceImpl extends ServiceImpl<OssUserRoleMapper, OssUserRole> implements OssUserRoleService {

    @Override
    public void add(OssUserRoleParam param){
        OssUserRole entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OssUserRoleParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OssUserRoleParam param){
        OssUserRole oldEntity = getOldEntity(param);
        OssUserRole newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OssUserRoleResult findBySpec(OssUserRoleParam param){
        return null;
    }

    @Override
    public List<OssUserRoleResult> findListBySpec(OssUserRoleParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OssUserRoleParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OssUserRole>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OssUserRoleParam param){
        return param.getUserAndRoleId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OssUserRole getOldEntity(OssUserRoleParam param) {
        return this.getById(getKey(param));
    }

    private OssUserRole getEntity(OssUserRoleParam param) {
        OssUserRole entity = new OssUserRole();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
