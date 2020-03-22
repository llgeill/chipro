package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.test.entity.Test;
import cn.spark.chipro.test.mapper.TestMapper;
import cn.spark.chipro.test.model.params.TestParam;
import cn.spark.chipro.test.model.result.TestResult;
import  cn.spark.chipro.test.service.TestService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.websocket.api.feign.WebSocketFeignService;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private WebSocketFeignService webSocketFeignService;

    @Transactional
    @GlobalTransactional
    @Override
    public void dt() {
        TestParam testParam1 = new TestParam();
        testParam1.setTestContent("11111");
        testParam1.setTestName("22222");
        this.add(testParam1);
        MessageVO messageVO = new MessageVO();
        List<String> ids = new ArrayList<>();
        ids.add("1");
        messageVO.setUserIds(ids);
        messageVO.setMessageTime(new Date());
        messageVO.setText("测试分布式事务");
        Result push = webSocketFeignService.push(messageVO);
        throw new CoreException();
    }

    @Override
    public void add(TestParam param){
        Test entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TestParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TestParam param){
        Test oldEntity = getOldEntity(param);
        Test newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TestResult findBySpec(TestParam param){
        return null;
    }

    @Override
    public List<TestResult> findListBySpec(TestParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(TestParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Test>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(TestParam param){
        return param.getTestId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Test getOldEntity(TestParam param) {
        return this.getById(getKey(param));
    }

    private Test getEntity(TestParam param) {
        Test entity = new Test();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
