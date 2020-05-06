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
 * @author LCF
 * @since 2020-04-11
 */
@Data
public class TestParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 测验名称
     */
    private String name;

    /**
     * 测验说明(选填)
     */
    private String explain;

    /**
     * 截止时间
     */
    private Integer deadline;

    /**
     * 提交次数(0:无次数限制，大于0表示次数限制)
     */
    private Integer submitNum;

    /**
     * 1:批改后立即公布成绩 2:提交截止后公布成绩 3:手动公布成绩
     */
    private Integer resultsRevealStatus;

    /**
     * 是否公布答案、解析、批改评语(1:是，0:否)
     */
    private String resultsAnswer;

    /**
     * 创建时间
     */
    private Date createTime;

    private List<QuestionParam> questions;
}
