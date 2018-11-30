package com.medhead.kf100.model.dto;

public class AdminArticleSearchDto {

    private Integer type;
    private Long topLevelCategoryId;
    private Long secondLevelCategoryId;
    private Long begin;
    private Long end;
    private Integer status;
    private Integer title;
    private Integer current;
    private Integer pageSize;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AdminArticleSearchDto{" +
                "type=" + type +
                ", topLevelCategoryId=" + topLevelCategoryId +
                ", secondLevelCategoryId=" + secondLevelCategoryId +
                ", begin=" + begin +
                ", end=" + end +
                ", status=" + status +
                ", title=" + title +
                ", current=" + current +
                ", pageSize=" + pageSize +
                '}';
    }
}
