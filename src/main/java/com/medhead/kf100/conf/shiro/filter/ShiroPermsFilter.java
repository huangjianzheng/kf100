package com.medhead.kf100.conf.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.conf.shiro.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.medhead.kf100.conf.shiro.ShiroUtils.getJwt;

/**
 * 自定义权限过滤器  添加对ajax请求和jwt请求的处理
 *
 * @author ming
 * @since 2018-01-16
 */
public class ShiroPermsFilter extends PermissionsAuthorizationFilter {


    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = this.getSubject(request, response);
        String jwt = getJwt(request);
        if((null == subject || !subject.isAuthenticated()) && !StringUtils.isEmpty(jwt)) {
            AuthenticationToken token = new JwtToken(jwt, request.getRemoteHost());
            try {
                subject = getSubject(request, response);
                subject.login(token);
                return this.checkPerms(subject, mappedValue);
            } catch (AuthenticationException e) {
                return false;
            }
        } else {
            return checkPerms(subject, mappedValue);
        }
    }


    private boolean checkPerms(Subject subject, Object mappedValue) {
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if(perms != null && perms.length > 0) {
            if(perms.length == 1) {
                if(!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if(!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        StringBuffer requestURL = WebUtils.toHttp(servletRequest).getRequestURL();
        if(requestURL.toString().matches(".*api.*")) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(ResponseResult.Unauthorized("未授权")));
        } else {
            String unauthorizedUrl = this.getUnauthorizedUrl();
            if(StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(servletRequest, servletResponse, unauthorizedUrl);
            } else {
                WebUtils.toHttp(servletResponse).sendError(401);
            }
        }
        return false;

    }

}
