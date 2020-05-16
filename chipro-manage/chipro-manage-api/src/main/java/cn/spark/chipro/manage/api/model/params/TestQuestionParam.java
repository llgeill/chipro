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
public class TestQuestionParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String id;

    private String questionId;

    private String testId;
}
