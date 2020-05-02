package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 专项分类
     */
    @TableField("classify")
    private String classify;

    /**
     * 题目类型
     */
    @TableField("type")
    private String type;

    /**
     * 问题和答案(json格式保存)
     */
    @TableField("content")
    private String content;

    /**
     * 发布类型（默认0）：0-注册用户使用，1-非注册用户使用
     */
    @TableField("pub_type")
    private Integer pubType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPubType() {
        return pubType;
    }

    public void setPubType(Integer pubType) {
        this.pubType = pubType;
    }

    @Override
    public String toString() {
        return "Question{" +
        "id=" + id +
        ", classify=" + classify +
        ", type=" + type +
        ", content=" + content +
        ", pubType=" + pubType +
        "}";
    }
}
