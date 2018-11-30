package com.medhead.kf100.conf.shiro;


import com.medhead.kf100.conf.shiro.filter.ShiroAuthcFilter;
import com.medhead.kf100.conf.shiro.filter.ShiroPermsFilter;
import com.medhead.kf100.conf.shiro.realm.JwtRealm;
import com.medhead.kf100.conf.shiro.realm.UserRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * shiro配置类
 * <p>
 * cookie-session/jwt   支持ajax 请求
 */
@Configuration
@ConditionalOnProperty(name = "shiro.type", havingValue = "agile")
public class ShiroConfig implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    private ShiroProperties shiroProperties = new ShiroProperties();


    @Override
    public void setEnvironment(Environment environment) {
        this.shiroProperties.setLoginUrl(environment.getProperty("shiro.login-url"));
        this.shiroProperties.setSuccessUrl(environment.getProperty("shiro.success-url"));
        this.shiroProperties.setUnauthorizedUrl(environment.getProperty("shiro.unauthorized-url"));
    }


    @Bean(name = "ehcache")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(false);
        return ehCacheManagerFactoryBean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }


    @Bean("userRealm")
    public UserRealm getUserRealm(EhCacheManager ehCacheManager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        userRealm.setAuthorizationCacheName("authorizationCache");
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setCacheManager(ehCacheManager);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashAlgorithmName("MD5");
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean("jwtRealm")
    public JwtRealm getJwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCachingEnabled(false);
        return jwtRealm;
    }


    /**
     * 会话相关
     * <p>
     * 会话id生成器
     */
    @Bean("sessionIdGenerator")
    public SessionIdGenerator getSessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }


    /**
     * 会话相关
     * 会话DAO
     */
    @Bean("SessionDAO")
    public EnterpriseCacheSessionDAO getSessionDao(SessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    /**
     * 会话相关
     * 会话Cookie模板
     */
    @Bean("sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        return cookie;
    }


    @Bean("sessionManager")
    public DefaultWebSessionManager getSessionManager(EnterpriseCacheSessionDAO sessionDAO, SimpleCookie sessionIdCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(2 * 3600 * 1000); //2h
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


    @Bean("sessionValidationScheduler")
    public QuartzSessionValidationScheduler getSessionValidationScheduler(DefaultWebSessionManager sessionManager) {
        QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
        sessionValidationScheduler.setSessionValidationInterval(2 * 3600 * 1000);   //  2h
        sessionValidationScheduler.setSessionManager(sessionManager);
        return sessionValidationScheduler;
    }

    @Bean("securityManager")
    public SecurityManager getSecurityManager(UserRealm userRealm, JwtRealm jwtRealm, EhCacheManager ehCacheManager, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        SessionStorageEvaluator sessionStorageEvaluator = subjectDAO.getSessionStorageEvaluator();
        securityManager.setSubjectFactory(new AgileSubjectFactory(sessionStorageEvaluator));
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(jwtRealm);
        securityManager.setRealms(realms);
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager);
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
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        kickoutSessionControlFilter.setSessionManager(sessionManager);
//        kickoutSessionControlFilter.setCacheManager(ehCacheManager);
//        kickoutSessionControlFilter.setUserRealm(userRealm);
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new ShiroAuthcFilter());
        filters.put("perms", new ShiroPermsFilter());
//      filters.put("kickout", kickoutSessionControlFilter);
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


        logger.debug("shrioFilter inited");
        return shiroFilterFactoryBean;
    }


}
