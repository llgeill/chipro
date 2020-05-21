package cn.spark.chipro.manage.api.model.params;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author 廖超凡
 * @since 2020-05-01
 */
@Data
public class CurriculumParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 课程名
     */
    private String name;

    /**
     * 课程图片
     */
    private String img;

    /**
     * 课程类型
     */
    private String type;

    /**
     * 点击数
     */
    private Integer hits;

    /**
     * 点赞数
     */
    private Integer likenum;

    private Date createTime;

    private List<CurriculumItemParam> curriculumItems;
}
