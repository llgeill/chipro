package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 廖超凡
 * @since 2020-05-01
 */
@TableName("curriculum")
public class Curriculum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 课程名
     */
    @TableField("`name`")
    private String name;

    /**
     * 课程图片
     */
    @TableField("img")
    private String img;

    /**
     * 课程类型
     */
    @TableField("type")
    private String type;

    /**
     * 点击数
     */
    @TableField("hits")
    private Integer hits;

    /**
     * 点赞数
     */
    @TableField("likenum")
    private Integer likenum;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private List<CurriculumItem> curriculumItems;


    public List<CurriculumItem> getCurriculumItems() {
        return curriculumItems;
    }

    public void setCurriculumItems(List<CurriculumItem> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
        "id=" + id +
        ", name=" + name +
        ", img=" + img +
        ", type=" + type +
        ", hits=" + hits +
        ", likenum=" + likenum +
        ", createTime=" + createTime +
        "}";
    }
}
