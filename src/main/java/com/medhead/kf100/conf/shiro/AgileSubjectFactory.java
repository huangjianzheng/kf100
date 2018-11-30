package com.medhead.kf100.conf.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 自定义subject工厂类  支持 session-cookie和无状态(jwt)两种认证授权模式
 **/
public class AgileSubjectFactory extends DefaultWebSubjectFactory {

    //  private DefaultWebSessionManager sessionManager;
    private DefaultSessionStorageEvaluator storageEvaluator;

    public AgileSubjectFactory(SessionStorageEvaluator storageEvaluator) {
        this.storageEvaluator = (DefaultSessionStorageEvaluator) storageEvaluator;
        // this.sessionManager = sessionManager;
    }

    @Override
    public Subject createSubject(SubjectContext context) {
        AuthenticationToken token = context.getAuthenticationToken();
        if((token instanceof JwtToken)) {  // 如果是 jwt 请求
            context.setSessionCreationEnabled(false);   // 不创建session
            //    sessionManager.setSessionIdCookieEnabled(false);  // 不生成 cookie
            storageEvaluator.setSessionStorageEnabled(false);  // 不存储 session
        } else {
            storageEvaluator.setSessionStorageEnabled(true);
            context.setSessionCreationEnabled(true);
            //  sessionManager.setSessionIdCookieEnabled(true);
        }
        return super.createSubject(context);
    }

}
