package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 课程标题
     */
	private String title;
    /**
     * 课程封面
     */
	private String logo;
    /**
     * 课程简介
     */
	private String introduction;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 观看人数
     */
	@TableField("visit_num")
	private Integer visitNum;
    /**
     * 0.显示，1隐藏
     */
	private Integer status;
    /**
     * 讲师姓名
     */
	@TableField("lecturer_name")
	private String lecturerName;


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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Course{" +
			", id=" + id +
			", title=" + title +
			", logo=" + logo +
			", introduction=" + introduction +
			", createTime=" + createTime +
			", visitNum=" + visitNum +
			", status=" + status +
			", lecturerName=" + lecturerName +
			"}";
	}
}
