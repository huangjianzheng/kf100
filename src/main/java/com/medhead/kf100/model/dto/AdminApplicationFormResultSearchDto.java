package com.medhead.kf100.model.dto;

public class AdminApplicationFormResultSearchDto {
   private String userName;
   private Long start;
   private Long end;
   private Integer type;
   private Integer status;
   private Integer current;
   private Integer pageSize;

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Long getStart() {
      return start;
   }

   public void setStart(Long start) {
      this.start = start;
   }

   public Long getEnd() {
      return end;
   }

   public void setEnd(Long end) {
      this.end = end;
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
}
