package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
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
     * 发布人姓名
     */
	@TableField("publisher_name")
	private String publisherName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 类型
     */
	private Integer type;
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
                ", publisherName='" + publisherName + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", status=" + status +
                ", topLevelCategoryId=" + topLevelCategoryId +
                ", secondLevelCategoryId=" + secondLevelCategoryId +
                ", topLevelCategoryName='" + topLevelCategoryName + '\'' +
                ", secondLevelCategoryName='" + secondLevelCategoryName + '\'' +
                '}';
    }
}
