package cn.spark.chipro.core.dao.mybatis.config;

import cn.spark.chipro.core.util.UserContext;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * <p>配置字段自动填充处理类</p>
 *
 * @author : liliguang
 * @date : 2019-11-04 13:07
 **/

public class AutoMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime",new Date(),metaObject);
        setFieldValByName("createDate",new Date(),metaObject);
        if(UserContext.getUserInfo()!=null){
            setFieldValByName("createPerson", UserContext.getUserInfo().get("userId"),metaObject);
        }
        if(UserContext.getUserInfo()!=null){
            setFieldValByName("createUser", UserContext.getUserInfo().get("userId"),metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new Date(),metaObject);
        setFieldValByName("updateDate",new Date(),metaObject);
        if(UserContext.getUserInfo()!=null){
            setFieldValByName("updatePerson", UserContext.getUserInfo().get("userId"),metaObject);
        }
        if(UserContext.getUserInfo()!=null){
            setFieldValByName("updateUser", UserContext.getUserInfo().get("userId"),metaObject);
        }
    }
}
