package cn.spark.chipro.community.biz.service;

import cn.spark.chipro.community.api.model.params.ProductUser;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.community.biz.entity.Production;
import cn.spark.chipro.community.api.model.params.ProductionParam;
import cn.spark.chipro.community.api.model.result.ProductionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 作品 服务类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
public interface ProductionService extends IService<Production> {

    /**
     * 新增
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void add(ProductionParam param);

    /**
     * 删除
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void delete(ProductionParam param);

    /**
     * 更新
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    void update(ProductionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    ProductionResult findBySpec(ProductionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<ProductionResult> findListBySpec(ProductionParam param);

    /**
     * 查询list
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    List<Production> findListByStudentId(String studentId);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 李利光
     * @Date 2020-02-05
     */
    PageInfo findPageBySpec(ProductionParam param);

    /**
     * 作品评论
     * @param productUser
     */
    public void comment(ProductUser productUser);

    /**
     * 作品点赞
     * @param productUser
     */
    void giveLike(ProductUser productUser);

    boolean fabulousProduct(ProductUser productUser);

    /**
     * 作品评论
     * @param param
     */
    void comment(ProductionParam param);

}
