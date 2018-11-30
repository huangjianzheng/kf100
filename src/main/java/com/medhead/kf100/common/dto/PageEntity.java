package com.medhead.kf100.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "分页实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageEntity<T> {

    @ApiModelProperty(notes = "页码", example = "页码 (类型：int)", position = 1)
    private Integer current;

    @ApiModelProperty(notes = "总页数", example = "总页数 (类型：int)", position = 2)
    private Integer pages;

    @ApiModelProperty(notes = "每页显示的查询结果量", example = "每页显示的查询结果量 (类型：int)", position = 3)
    private Integer size;

    @ApiModelProperty(notes = "查询结果总数", example = "查询结果总数 (类型：int)", position = 4)
    private Integer total;

    @ApiModelProperty(notes = "查询结果数组", position = 5)
    private List<T> records;

    public PageEntity(Page<T> page) {
        this.current = page.getCurrent();
        this.pages = page.getPages();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.records = page.getRecords();
    }

    public PageEntity() {
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
