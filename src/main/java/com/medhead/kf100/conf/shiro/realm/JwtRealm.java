package com.medhead.kf100.conf.shiro.realm;


import com.medhead.kf100.conf.shiro.JwtToken;
import com.medhead.kf100.conf.shiro.MyPrincipal;
import com.medhead.kf100.conf.shiro.ShiroUtils;
import com.medhead.kf100.model.Token;
import com.medhead.kf100.service.TokenService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import javax.servlet.ServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 基于JWT的认证域
 */
public class JwtRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(JwtRealm.class);

    @Value("${jwt.secret}")
    private String jwtSecret;  // jwt 私钥


    @Autowired
    @Lazy
    private TokenService tokenService;

    @Override
    public boolean supports(AuthenticationToken token) {   // 设置当前认证域 支持 校验 的  token
        return token != null && (token instanceof JwtToken);
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        LOG.info("jwtRealm 认证.....");
        MyPrincipal myPrincipal;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                    .parseClaimsJws(jwt)
                    .getBody();
            myPrincipal = createMyPrincipal(jwt, claims);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT 令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT 令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT 令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT 令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT 令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
        // 判断token 是否已被移除
        Token model = tokenService.selectById(myPrincipal.getId());
        if(model == null) {
            throw new AuthenticationException("JWT 令牌无效:令牌已被移除");
        }
        return new SimpleAuthenticationInfo(myPrincipal, Boolean.TRUE, getName());
    }

    /**
     * 构建认证主体对象
     */
    private MyPrincipal createMyPrincipal(String jwt, Claims claims) {
        MyPrincipal myPrincipal = new MyPrincipal();
        myPrincipal.setJwt(jwt);// 用户id
        myPrincipal.setType(1);
        myPrincipal.setId(Long.parseLong(claims.getId()));  // token id
        myPrincipal.setUserId(Long.parseLong(claims.getSubject()));// 用户id
        myPrincipal.setIssuer(claims.getIssuer());// 签发者
        myPrincipal.setIssuedAt(claims.getIssuedAt());// 签发时间
        myPrincipal.setPerms(claims.get("perms", String.class));// 访问主张-权限
        return myPrincipal;
    }

    /**
     * 授权,JWT已包含访问主张只需要解析其中的主张定义就行了
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        MyPrincipal myPrincipal = (MyPrincipal) principals.getPrimaryPrincipal();
        if(!myPrincipal.getType().equals(1)) {
            return null;
        }
        LOG.info("jwtRealm 授权.....");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 解析权限并设置
        String perms = myPrincipal.getPerms();
        if(StringUtils.isNotBlank(perms)) {
            HashSet<String> set = new HashSet<>(Arrays.asList(perms.split(",")));
            authorizationInfo.setStringPermissions(set);
        }
        return authorizationInfo;
    }

    public MyPrincipal getCurrentPrincipal(ServletRequest request) {
        MyPrincipal principal = (MyPrincipal) SecurityUtils.getSubject().getPrincipal();
        if(principal != null) {
            return principal;
        }
        String jwt = ShiroUtils.getJwt(request);
        if(StringUtils.isEmpty(jwt)) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                    .parseClaimsJws(jwt)
                    .getBody();
            principal = createMyPrincipal(jwt, claims);
        } catch (Exception e) {
            return null;
        }
         // 判断token 是否已被移除
        Token model = tokenService.selectById(principal.getId());
        if(model == null) {
            return null;
        }
        return principal;
    }
}
