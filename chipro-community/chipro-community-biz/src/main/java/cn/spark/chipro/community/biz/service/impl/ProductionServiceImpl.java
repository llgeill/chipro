package cn.spark.chipro.community.biz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.spark.chipro.community.api.model.params.ProductUser;
import cn.spark.chipro.community.biz.entity.Production;
import cn.spark.chipro.community.biz.mapper.ProductionMapper;
import cn.spark.chipro.community.api.model.params.ProductionParam;
import cn.spark.chipro.community.api.model.result.ProductionResult;
import  cn.spark.chipro.community.biz.service.ProductionService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.core.util.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 作品 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(ProductionParam param){
        Production entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ProductionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ProductionParam param){
        Production oldEntity = getOldEntity(param);
        Production newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProductionResult findBySpec(ProductionParam param){
        return null;
    }

    @Override
    public List<ProductionResult> findListBySpec(ProductionParam param){
        return null;
    }

    @Override
    public List<Production> findListByStudentId(String studentId){
        QueryWrapper<Production> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("USER_ID",studentId.split(","));
        return this.list(queryWrapper);
    }

    @Override
    public PageInfo findPageBySpec(ProductionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Production>objectQueryWrapper=new QueryWrapper<>();
        if(param!=null){
            if(StringUtil.isNotEmpty(param.getUserId())){
                objectQueryWrapper.eq("USER_ID",param.getUserId());
            }
            if(StringUtil.isNotEmpty(param.getName())){
                objectQueryWrapper.like("NAME",param.getName());
            }
            objectQueryWrapper.orderByDesc("GLIKE","CLICK");
        }
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    /**
     * key 为作品Id，value为评论内容以及用户id
     * @param productUser
     */
    @Override
    public void comment(ProductUser productUser){
        //评论存入list集合中
        productUser.setProductionId(productUser.getProductionId());
        Map userInfo = UserContext.getUserInfo();
        if(userInfo!=null){
            if(userInfo.get("userName")!=null){
                productUser.setUserName(userInfo.get("userName").toString());
            }
            if(userInfo.get("userNameAlias")!=null){
                productUser.setUserName(userInfo.get("userNameAlias").toString());
            }

        }
        productUser.setDate(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        redisTemplate.opsForList().leftPush("comment_"+productUser.getProductionId(),productUser);
    }

    @Override
    public List getComment(ProductUser param) {
        Long size = redisTemplate.opsForList().size("comment_" + param.getProductionId());
        List range = redisTemplate.opsForList().range("comment_" + param.getProductionId(), 0, size);
        return range;
    }

    /**
     * key 为作品Id，value为用户集
     * 1.用户对某个博客进行点赞
     * 2.先进行判断，该用户是否点赞过改博客
     * 3.如果没有，则将该用户添加到redis set中的用户集
     * 4.如果有，则删除用户集中的用户
     * @param productUser
     */
    @Override
    public void giveLike(ProductUser productUser) {
        Object o = redisTemplate.opsForValue().get("fabulous_count_" + productUser.getProductionId());
        boolean flag = this.fabulousProduct(productUser);
        if(flag){
            //点赞数增加
            redisTemplate.opsForValue().increment("fabulous_count_"+productUser.getProductionId(),1);
        }else{
            //点赞数减少
            redisTemplate.opsForValue().increment("fabulous_count_"+productUser.getProductionId(),-1);
        }


    }

    //用户点赞作品
    @Override
    public boolean fabulousProduct(ProductUser productUser)
    {
        boolean bl;
        boolean flag = redisTemplate.opsForSet().isMember("fabulous_"+productUser.getProductionId(),"user_"+productUser.getUserId());
        if (flag)
        {
            redisTemplate.opsForSet().remove("fabulous_"+productUser.getProductionId(),"user_"+productUser.getUserId());
            bl = false;
        }else {
            redisTemplate.opsForSet().add("fabulous_"+productUser.getProductionId(),"user_"+productUser.getUserId());
            bl = true;
        }
        return bl;
    }




    private Serializable getKey(ProductionParam param){
        return param.getProductionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Production getOldEntity(ProductionParam param) {
        return this.getById(getKey(param));
    }

    private Production getEntity(ProductionParam param) {
        Production entity = new Production();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
