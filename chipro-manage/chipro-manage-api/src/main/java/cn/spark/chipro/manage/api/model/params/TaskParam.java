package cn.spark.chipro.manage.api.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 任务
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Data
public class TaskParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 任务编码
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 指派课室
     */
    private String assignedClass;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 作品提交数量
     */
    private String worksCount;

    /**
     * 作品要求
     */
    private String worksRequire;

    /**
     * 编辑器版本
     */
    private String editor;

    /**
     * 资源编码
     */
    private String resourceId;

    /**
     * 截止时间
     */
    private Date deadline;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remarks;
}
