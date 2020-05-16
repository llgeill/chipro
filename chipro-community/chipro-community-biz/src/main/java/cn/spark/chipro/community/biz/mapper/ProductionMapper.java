package cn.spark.chipro.community.biz.mapper;

import cn.spark.chipro.community.biz.entity.Production;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 作品 Mapper 接口
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface ProductionMapper extends BaseMapper<Production> {

    @Select("select USER_NAME_ALIAS from OSS_USER where USER_ID = #{id}")
    String getUserNameById(String id);
}
