package com.medhead.kf100.conf.shiro;

import java.security.Principal;
import java.util.Date;
import java.util.Objects;

/**
 * 认证主体实体对象
 */
public class MyPrincipal implements Principal {


    private Long id;    // tokenId
    private Long userId;  // 用户id
    private String issuer;  // 签发者
    private Date issuedAt;  // 签发时间
    private String perms;     // 拥有权限
    private Integer type;  // 0 :userRealm 1:jwtRealm
    private String jwt;  // 0 :userRealm 1:jwtRealm

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String getName() {
        return this.userId.toString();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof MyPrincipal)) return false;
        MyPrincipal myPrincipal = (MyPrincipal) o;
        return Objects.equals(userId, myPrincipal.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "MyPrincipal{" +
                "id=" + id +
                ", userId=" + userId +
                ", issuer='" + issuer + '\'' +
                ", issuedAt=" + issuedAt +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
