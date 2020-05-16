package cn.spark.chipro.test.model.params;

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
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class TestParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private String testId;

    /**
     * 名称
     */
    private String testName;

    /**
     * 内容
     */
    private String testContent;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private Date updatePerson;
}
