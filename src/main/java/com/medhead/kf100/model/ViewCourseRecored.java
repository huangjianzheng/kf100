package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@TableName("view_course_recored")
public class ViewCourseRecored extends Model<ViewCourseRecored> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 课程章节
     */
	@TableField("course_chapter_id")
	private Long courseChapterId;
    /**
     * 当前进度时间
     */
	@TableField("current_progress_time")
	private Long currentProgressTime;
    /**
     * 最大进度时间
     */
	@TableField("max_progress_time")
	private Long maxProgressTime;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseChapterId() {
		return courseChapterId;
	}

	public void setCourseChapterId(Long courseChapterId) {
		this.courseChapterId = courseChapterId;
	}

	public Long getCurrentProgressTime() {
		return currentProgressTime;
	}

	public void setCurrentProgressTime(Long currentProgressTime) {
		this.currentProgressTime = currentProgressTime;
	}

	public Long getMaxProgressTime() {
		return maxProgressTime;
	}

	public void setMaxProgressTime(Long maxProgressTime) {
		this.maxProgressTime = maxProgressTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ViewCourseRecored{" +
			", id=" + id +
			", courseChapterId=" + courseChapterId +
			", currentProgressTime=" + currentProgressTime +
			", maxProgressTime=" + maxProgressTime +
			", userId=" + userId +
			"}";
	}
}
