package com.medhead.kf100.conf.shiro.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.medhead.kf100.conf.shiro.MyPrincipal;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.service.SysResourceService;
import com.medhead.kf100.service.SysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * session认证域
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    @Autowired
    @Lazy
    private SysResourceService sysResourceService;


    @Override
    public boolean supports(AuthenticationToken token) {   // 设置当前认证域 支持 校验 的  token
        return token != null && (token instanceof UsernamePasswordToken);
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LOG.info("当前认证用户信息为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        SysUser user = new SysUser();
        try {
            user.setAccount(token.getUsername());
            user = sysUserService.selectOne(new EntityWrapper<>(user));
        } catch (Exception e) {
            throw new AuthenticationException();
        }
        if(user == null) {
            throw new AuthenticationException();
        }
        if(user.getStatus().equals(0)) {
            throw new LockedAccountException();
        }

        MyPrincipal myPrincipal = new MyPrincipal();
        myPrincipal.setType(0);
        myPrincipal.setUserId(user.getId());
        return new SimpleAuthenticationInfo(myPrincipal, user.getPassWord(), ByteSource.Util.bytes(user.getAccount()), this.getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        MyPrincipal myPrincipal = (MyPrincipal) principalCollection.getPrimaryPrincipal();
        if(!myPrincipal.getType().equals(0)) {
            return null;
        }
        LOG.info("userRealm授权.....");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> list = sysResourceService.findAllPermissionByUserId(myPrincipal.getUserId());
        LOG.debug(ArrayUtils.toString(list));
        if(list != null) {
            String permissions = StringUtils.join(list.toArray(), ",");
            myPrincipal.setPerms(permissions);
            for (String perm : list) {
                authorizationInfo.addStringPermission(perm);
            }
        }
        return authorizationInfo;
    }


    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }

    @Override
    protected Collection<Permission> getPermissions(AuthorizationInfo info) {
        return super.getPermissions(info);
    }

    public List<String> getPermissions() {
        AuthorizationInfo authorizationInfo = super.getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        Collection<Permission> permissionList = super.getPermissions(authorizationInfo);
        List<String> permissions = new ArrayList<>();
        for (Permission p : permissionList) {
            permissions.add(p.toString());
        }
        return permissions;
    }

//    @Override
//    protected void clearCache(PrincipalCollection principals) {
//        super.clearCache(principals);
//    }
}
