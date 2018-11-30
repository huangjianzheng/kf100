package com.medhead.kf100.conf.shiro;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * shrio 配置属性实体类
 *
 * @author ming
 * @since 2017-12-08
 */
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    private String loginUrl;   // 登录页面url

    private String successUrl;   //登录成功跳转页面url

    private String unauthorizedUrl;  //授权未通过跳转页面url

    private Map<String, String> FilterChainDefinitionMap; //shrio权限管理过滤器链

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public Map<String, String> getFilterChainDefinitionMap() {
        return FilterChainDefinitionMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> FilterChainDefinitionMap) {
        this.FilterChainDefinitionMap = FilterChainDefinitionMap;
    }

    @Override
    public String toString() {
        return "ShiroProperties{" +
                "loginUrl='" + loginUrl + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", unauthorizedUrl='" + unauthorizedUrl + '\'' +
                ", FilterChainDefinitionMap=" + FilterChainDefinitionMap +
                '}';
    }
}
