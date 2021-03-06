package com.medhead.kf100.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 图片
     */
	private String logo;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
	private Date createTime;
    /**
     * 标题
     */
	private String title;
    /**
     * 简介
     */
	private String introduction;
    /**
     *类型 (1:轮播图，2：讲师)
     */
	private Integer type;
    /**
     * 链接
     */
	private String url;
    /**
     * 序号
     */
	private Integer sequence;
    /**
     * 标签
     */
	private String tag;
    /**
     * 状态（0：未发布，1：已发布）
     */
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date  createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    @TableLogic
    private Integer deleted; //0:正常 1:删除


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
	protected Serializable pkVal() {
		return this.id;
	}

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", logo='" + logo + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", sequence=" + sequence +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                '}';
    }
}
