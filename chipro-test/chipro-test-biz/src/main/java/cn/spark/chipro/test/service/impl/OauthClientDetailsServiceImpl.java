package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OauthClientDetails;
import cn.spark.chipro.test.mapper.OauthClientDetailsMapper;
import cn.spark.chipro.test.model.params.OauthClientDetailsParam;
import cn.spark.chipro.test.model.result.OauthClientDetailsResult;
import  cn.spark.chipro.test.service.OauthClientDetailsService;
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
 *  服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

    @Override
    public void add(OauthClientDetailsParam param){
        OauthClientDetails entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OauthClientDetailsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OauthClientDetailsParam param){
        OauthClientDetails oldEntity = getOldEntity(param);
        OauthClientDetails newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OauthClientDetailsResult findBySpec(OauthClientDetailsParam param){
        return null;
    }

    @Override
    public List<OauthClientDetailsResult> findListBySpec(OauthClientDetailsParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OauthClientDetailsParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OauthClientDetails>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OauthClientDetailsParam param){
        return param.getClientId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OauthClientDetails getOldEntity(OauthClientDetailsParam param) {
        return this.getById(getKey(param));
    }

    private OauthClientDetails getEntity(OauthClientDetailsParam param) {
        OauthClientDetails entity = new OauthClientDetails();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
