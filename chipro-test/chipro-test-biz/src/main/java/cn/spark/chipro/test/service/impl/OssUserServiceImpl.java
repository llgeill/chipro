package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OssUser;
import cn.spark.chipro.test.mapper.OssUserMapper;
import cn.spark.chipro.test.model.params.OssUserParam;
import cn.spark.chipro.test.model.result.OssUserResult;
import  cn.spark.chipro.test.service.OssUserService;
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
 * 用户表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Service
public class OssUserServiceImpl extends ServiceImpl<OssUserMapper, OssUser> implements OssUserService {

    @Override
    public void add(OssUserParam param){
        OssUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OssUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OssUserParam param){
        OssUser oldEntity = getOldEntity(param);
        OssUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OssUserResult findBySpec(OssUserParam param){
        return null;
    }

    @Override
    public List<OssUserResult> findListBySpec(OssUserParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OssUserParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OssUser>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OssUserParam param){
        return param.getUserId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OssUser getOldEntity(OssUserParam param) {
        return this.getById(getKey(param));
    }

    private OssUser getEntity(OssUserParam param) {
        OssUser entity = new OssUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
