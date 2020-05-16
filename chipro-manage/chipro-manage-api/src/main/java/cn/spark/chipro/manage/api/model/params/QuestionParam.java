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
 * @author 廖超凡
 * @since 2020-05-02
 */
@Data
public class QuestionParam implements Serializable{

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
     * 问题
     */
    private String content;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 发布类型（默认0）：0-注册用户使用，1-非注册用户使用
     */
    private Integer pubType;
}
