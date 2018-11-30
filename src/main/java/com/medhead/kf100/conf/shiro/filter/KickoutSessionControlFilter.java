package com.medhead.kf100.conf.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.conf.shiro.MyPrincipal;
import com.medhead.kf100.conf.shiro.realm.UserRealm;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import static com.medhead.kf100.conf.shiro.ShiroUtils.*;

public class KickoutSessionControlFilter extends AccessControlFilter {

    private String kickoutUrl = getLoginUrl(); // 踢出后到的地址 默认为登录地址
    private boolean kickoutAfter = false; // 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; // 同一个帐号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<Serializable, Deque<Serializable>> cache;
    private UserRealm userRealm;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickoutSessionCache");
    }

    public void setUserRealm(UserRealm userRealm) {
        this.userRealm = userRealm;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            // 如果没有登录，直接进行之后的流程
            return true;
        }

        if(isOptions(WebUtils.toHttp(request))) {
            // 如果是options请求,直接进行之后的流程
            return true;
        }

        if(isJwtSubmission(request)) {
            // 如果是jwt请求,直接进行之后的流程
            return true;
        }


        Session session = subject.getSession();
        MyPrincipal principal = (MyPrincipal) subject.getPrincipal();
        Serializable sessionId = session.getId();

        Deque<Serializable> deque = cache.get(principal.getUserId());
        if(deque == null) {
            deque = new LinkedList<>();
            cache.put(principal.getUserId(), deque);
        }

        // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        // 如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }

        // 如果被踢出了，直接退出，重定向到踢出后的地址
        if(session.getAttribute("kickout") != null) {
            // 会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            if(isAjax(httpRequest)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                ObjectMapper objectMapper = new ObjectMapper();
                if(isCors((HttpServletRequest) request)) {
                    setCorsResponse(WebUtils.toHttp(response), WebUtils.toHttp(request));
                }
                response.getWriter().write(objectMapper.writeValueAsString(ResponseResult.kickOut("你的账号在其他地方登录,你已经下线了")));
                return false;
            } else {
                WebUtils.issueRedirect(request, response, kickoutUrl);
                return false;
            }
        }

        return true;
    }
}