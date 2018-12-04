package com.medhead.kf100.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@TableName("application_form_result")
public class ApplicationFormResult extends Model<ApplicationFormResult> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型  1：种子基金申请  2：线下培训班报名 3:双创大赛报名
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建时间
     */
    @TableField("expire_time")
    private Date expireTime;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 状态 1：通过 2:不通过 3:过期
     */
    private Integer status;
    /**
     * 用户id
     */
    @TableField(exist = false)
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApplicationFormResult{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", expireTime=" + expireTime +
                ", userId=" + userId +
                ", status=" + status +
                ", userName=" + userName +
                '}';
    }
}
