package com.medhead.kf100.conf.shiro;


import com.medhead.kf100.mapper.SysResourceMapper;
import com.medhead.kf100.model.SysResource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ming
 * <p>
 * 从数据库获取 shiro 拦截器链
 * <p>
 * 注意: 缺少动态更新功能 (计划添加)
 */
public class FilterChainDefinitionMapMetaSource implements FactoryBean<Map<String, String>> {

    private static final Logger LOG = LoggerFactory.getLogger(FilterChainDefinitionMapMetaSource.class);
    private static final String PERMISSION_STRING = "authc,perms[\"{0}\"]";

    @Autowired
    @Lazy
    private SysResourceMapper sysResourceMapper;

    @Autowired
    @Lazy
    private ShiroProperties shiroProperties;

    @Override
    public Map<String, String> getObject() throws Exception {
        LOG.info(shiroProperties == null ? "shiroProperties 获取失败" : shiroProperties.toString());
        Map<String, String> map = new LinkedHashMap<>();
        // 获取配置文件中的拦截器链
        if(shiroProperties != null && shiroProperties.getFilterChainDefinitionMap() != null) {
            map.putAll(shiroProperties.getFilterChainDefinitionMap());
        }
        // 获取数据库中的拦截器链
        List<SysResource> list = sysResourceMapper.selectList(null);
        if(list != null) {
            for (SysResource sysResource : list) {
                // 如果不为空值添加到section中
                if(StringUtils.isNotEmpty(sysResource.getUrl()) && StringUtils.isNotEmpty(sysResource.getPermissions())) {
                    map.put(sysResource.getUrl(), MessageFormat.format(PERMISSION_STRING, sysResource.getPermissions()));
                }

            }
        }
        map.put("/**", "authc");
        LOG.info("FilterChainDefinitionMap inited");
        return map;

    }


    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}