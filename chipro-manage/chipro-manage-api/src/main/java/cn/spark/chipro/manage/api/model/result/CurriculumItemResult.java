package cn.spark.chipro.manage.api.model.result;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@Data
public class CurriculumItemResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 子课程Id
     */
    private String id;

    /**
     * 课程Id
     */
    private String curriculumId;

    private String name;

    private String desc;

    /**
     * 课程视频
     */
    private String vedio;

    /**
     * ppt资源
     */
    private String ppt;

    private Date createTime;

}
