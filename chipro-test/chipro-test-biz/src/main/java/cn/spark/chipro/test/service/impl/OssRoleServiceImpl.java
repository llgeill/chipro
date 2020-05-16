package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OssRole;
import cn.spark.chipro.test.mapper.OssRoleMapper;
import cn.spark.chipro.test.model.params.OssRoleParam;
import cn.spark.chipro.test.model.result.OssRoleResult;
import  cn.spark.chipro.test.service.OssRoleService;
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
 * 角色表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Service
public class OssRoleServiceImpl extends ServiceImpl<OssRoleMapper, OssRole> implements OssRoleService {

    @Override
    public void add(OssRoleParam param){
        OssRole entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OssRoleParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OssRoleParam param){
        OssRole oldEntity = getOldEntity(param);
        OssRole newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OssRoleResult findBySpec(OssRoleParam param){
        return null;
    }

    @Override
    public List<OssRoleResult> findListBySpec(OssRoleParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OssRoleParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OssRole>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OssRoleParam param){
        return param.getRoleId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OssRole getOldEntity(OssRoleParam param) {
        return this.getById(getKey(param));
    }

    private OssRole getEntity(OssRoleParam param) {
        OssRole entity = new OssRole();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
