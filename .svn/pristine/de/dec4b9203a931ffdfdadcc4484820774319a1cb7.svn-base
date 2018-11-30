package com.medhead.kf100.conf.shiro;


import com.medhead.kf100.conf.shiro.filter.ShiroAuthcFilter;
import com.medhead.kf100.conf.shiro.filter.ShiroPermsFilter;
import com.medhead.kf100.conf.shiro.realm.JwtRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shiro配置类
 * <p>
 * cookie-session/jwt   支持ajax 请求
 */
@Configuration
@ConditionalOnProperty(name = "shiro.type", havingValue = "stateless")
public class StatelessShiroConfig implements EnvironmentAware {

    private static final Logger LOG = LoggerFactory.getLogger(StatelessShiroConfig.class);

    private ShiroProperties shiroProperties;


    @Override
    public void setEnvironment(Environment environment) {
        this.shiroProperties = new ShiroProperties();
        this.shiroProperties.setLoginUrl(environment.getProperty("shiro.login-url"));
        this.shiroProperties.setSuccessUrl(environment.getProperty("shiro.success-url"));
        this.shiroProperties.setUnauthorizedUrl(environment.getProperty("shiro.unauthorized-url"));
    }


    @Bean("jwtRealm")
    public JwtRealm getJwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCachingEnabled(false);
        return jwtRealm;
    }


    @Bean("securityManager")
    public SecurityManager getSecurityManager(JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm);
        DefaultSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        securityManager.setSubjectFactory(new StatelessSubjectFactory());
        return securityManager;
    }


    /*
     * shiro生命周期处理器
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * shrio注解相关
     * <p>
     * springmvc 处理未授权异常
     */
    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        return ShiroUtils.getSimpleMappingExceptionResolver(shiroProperties.getUnauthorizedUrl());
    }

    /*shrio注解相关*/
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /*shrio注解相关*/
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean("filterChainDefinitionMapMetaSource")
    public FilterChainDefinitionMapMetaSource getFilterChainDefinitionMapMetaSource() {
        return new FilterChainDefinitionMapMetaSource();
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager, FilterChainDefinitionMapMetaSource filterChainDefinitionMapMetaSource) throws Exception {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置自定义过滤器
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new ShiroAuthcFilter());
        filters.put("perms", new ShiroPermsFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //登录页面
        if(StringUtils.isNotEmpty(shiroProperties.getLoginUrl())) {
            shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        }

        // 登录成功后要跳转的链接
        if(StringUtils.isNotEmpty(shiroProperties.getSuccessUrl())) {
            shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        }

        // 未授权界面
        if(StringUtils.isNotEmpty(shiroProperties.getUnauthorizedUrl())) {
            shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        }

        Map<String, String> map = filterChainDefinitionMapMetaSource.getObject();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        LOG.debug("stateless shiroFilter init");
        return shiroFilterFactoryBean;
    }


}
