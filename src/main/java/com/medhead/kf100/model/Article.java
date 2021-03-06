package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 简介
     */
	private String introduction;
    /**
     * 发布人姓名
     */
	@TableField("publisher_name")
	private String publisherName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 创建时间
     */
	@TableField(exist = false)
	private Date timeStamp;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 浏览次数
     */
	private Integer count;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 一级分类id
     */
	@TableField("top_level_category_id")
	private Long topLevelCategoryId;
	/**
	 * 二级分类id
	 */
	@TableField("second_level_category_id")
	private Long secondLevelCategoryId;
    /**
     * 一级分类名
     */
	@TableField("top_level_category_name")
	private String topLevelCategoryName;
    /**
     * 二级分类名
     */
	@TableField("second_level_category_name")
	private String secondLevelCategoryName;


    @TableLogic
    private Integer deleted; //0:正常 1:删除

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    public Long getTopLevelCategoryId() {
        return topLevelCategoryId;
    }

    public void setTopLevelCategoryId(Long topLevelCategoryId) {
        this.topLevelCategoryId = topLevelCategoryId;
    }

    public Long getSecondLevelCategoryId() {
        return secondLevelCategoryId;
    }

    public void setSecondLevelCategoryId(Long secondLevelCategoryId) {
        this.secondLevelCategoryId = secondLevelCategoryId;
    }

    public String getTopLevelCategoryName() {
		return topLevelCategoryName;
	}

	public void setTopLevelCategoryName(String topLevelCategoryName) {
		this.topLevelCategoryName = topLevelCategoryName;
	}

	public String getSecondLevelCategoryName() {
		return secondLevelCategoryName;
	}

	public void setSecondLevelCategoryName(String secondLevelCategoryName) {
		this.secondLevelCategoryName = secondLevelCategoryName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
	protected Serializable pkVal() {
		return this.id;
	}

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", introduction='" + introduction + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", count=" + count +
                ", status=" + status +
                ", topLevelCategoryId=" + topLevelCategoryId +
                ", secondLevelCategoryId=" + secondLevelCategoryId +
                ", topLevelCategoryName='" + topLevelCategoryName + '\'' +
                ", secondLevelCategoryName='" + secondLevelCategoryName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
