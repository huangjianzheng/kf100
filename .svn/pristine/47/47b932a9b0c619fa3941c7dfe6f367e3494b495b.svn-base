package com.medhead.kf100.conf.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.conf.shiro.JwtToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static com.medhead.kf100.conf.shiro.ShiroUtils.getJwt;


/**
 * 自定义认证过滤器  添加对ajax请求的处理 以及对jwt认证的处理
 *
 * @author ming
 * @since 2018-01-16
 */
public class ShiroAuthcFilter extends FormAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ShiroAuthcFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String jwt = getJwt(request);
        if(!StringUtils.isEmpty(jwt)) {
            LOG.debug(jwt);
            LOG.debug(request.getRemoteHost());
            AuthenticationToken token = new JwtToken(jwt, request.getRemoteHost());
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (Exception e) {
                LOG.info(e.toString());
            }
        }
        StringBuffer requestURL = WebUtils.toHttp(request).getRequestURL();
        if(requestURL.toString().matches(".*api.*")) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            WebUtils.toHttp(response).getWriter().write(objectMapper.writeValueAsString(ResponseResult.unLogin("未登录")));
        } else {
            String loginUrl = this.getLoginUrl();
            if(StringUtils.hasText(loginUrl)) {
                WebUtils.issueRedirect(request, response, loginUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }
        return false;
    }


}
