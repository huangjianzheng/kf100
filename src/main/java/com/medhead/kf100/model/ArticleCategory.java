package com.medhead.kf100.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@TableName("article_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCategory extends Model<ArticleCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 名字
     */
	private String name;
    /**
     * 父类型id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 层级
     */
	private Integer level;

	@TableField(exist = false)
	private List<ArticleCategory> childrens;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

    public List<ArticleCategory> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ArticleCategory> childrens) {
        this.childrens = childrens;
    }

    @Override
	protected Serializable pkVal() {
		return this.id;
	}

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", childrens=" + childrens +
                '}';
    }
}
