package cn.spark.chipro.auth.mapper;

import cn.spark.chipro.auth.entity.PermissionInfo;
import cn.spark.chipro.auth.entity.RoleInfo;
import cn.spark.chipro.auth.entity.User;
import cn.spark.chipro.auth.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 李利光
 * @since 2019-11-06
 */
public interface UserMapper extends BaseMapper<User> {

    UserInfo selectUserByUserName(@Param("userName") String userName);

    List<RoleInfo> selectRoleByUserId(@Param("userId") String userId);

    List<PermissionInfo> selectPermissionByUserId(@Param("userId") String userId);

}
