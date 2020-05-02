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
public class QuestionResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 专项分类
     */
    private String classify;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 问题和答案(json格式保存)
     */
    private String content;

    /**
     * 发布类型（默认0）：0-注册用户使用，1-非注册用户使用
     */
    private Integer pubType;

}
