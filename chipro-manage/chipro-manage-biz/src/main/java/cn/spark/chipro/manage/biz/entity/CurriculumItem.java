package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@TableName("curriculum_item")
public class CurriculumItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 子课程Id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 课程Id
     */
    @TableField("curriculum_id")
    private String curriculumId;

    @TableField("`name`")
    private String name;

    @TableField("`desc`")
    private String desc;

    /**
     * 课程视频
     */
    @TableField("vedio")
    private String vedio;

    /**
     * ppt资源
     */
    @TableField("ppt")
    private String ppt;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CurriculumItem{" +
        "id=" + id +
        ", curriculumId=" + curriculumId +
        ", name=" + name +
        ", desc=" + desc +
        ", vedio=" + vedio +
        ", ppt=" + ppt +
        ", createTime=" + createTime +
        "}";
    }
}
