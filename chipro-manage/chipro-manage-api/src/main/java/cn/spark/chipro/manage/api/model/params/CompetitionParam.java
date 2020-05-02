package cn.spark.chipro.manage.api.model.params;

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
public class CompetitionParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 大赛名称
     */
    private String name;

    /**
     * 大赛详情
     */
    private String desc;

    /**
     * 报名链接
     */
    private String signUpLink;
}
