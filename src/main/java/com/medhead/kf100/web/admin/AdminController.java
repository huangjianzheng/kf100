package com.medhead.kf100.web.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.common.util.JwtUtils;
import com.medhead.kf100.common.util.MD5Utils;
import com.medhead.kf100.conf.shiro.MyPrincipal;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.model.Token;
import com.medhead.kf100.model.vo.UserInfoVo;
import com.medhead.kf100.service.SysResourceService;
import com.medhead.kf100.service.SysRoleService;
import com.medhead.kf100.service.SysUserService;
import com.medhead.kf100.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ming
 * @since 2018-11-26
 */
@RestController
@RequestMapping("api/admin")
@ApiIgnore
public class AdminController {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.period}")
    private Long jwtPeriod;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseResult login(String username, String password) {
        if(StringUtils.isEmpty(username)) {
            return ResponseResult.badRequest("用户名为空");
        }
        if(StringUtils.isEmpty(password)) {
            return ResponseResult.badRequest("密码为空");
        }
        SysUser model = new SysUser();
        model.setAccount(username);
        model.setStatus(1);
        model.setUserType(3);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<>(model));
        if(sysUser == null) {
            return ResponseResult.badRequest("用户名不存在");
        }
        String pwd = MD5Utils.MD5EncodePwd(password, username);
        if(!pwd.equals(sysUser.getPassWord())) {
            return ResponseResult.badRequest("密码错误");
        }
        Token condition = new Token();
        condition.setUserId(sysUser.getId());
        tokenService.delete(new EntityWrapper<>(condition));
        Token token = new Token();
        token.setUserId(sysUser.getId());
        Date now = new Date();
        token.setCreateTime(now);
        Long expireMillis = now.getTime() + jwtPeriod;
        Date expireTime = new Date(expireMillis);
        token.setExpireTime(expireTime);
        tokenService.insert(token);
        List<String> permList = sysResourceService.findAllPermissionByUserId(sysUser.getId());
        String perms = null;
        if(permList != null && permList.size() > 0) {
            perms = StringUtils.join(permList.toArray(), ",");
        }
        String jwt = JwtUtils.issueJwt(token.getId(), token.getUserId(), perms, jwtSecret, jwtPeriod, now.getTime());
        return ResponseResult.success(jwt);
    }

    @PostMapping("userInfo")
    public ResponseResult getUserInfo() {
        MyPrincipal principal = (MyPrincipal) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = sysUserService.selectById(principal.getUserId());
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setName(sysUser.getUserName());
        userInfoVo.setRoles(sysRoleService.findAllRoleTypeByUserId(principal.getUserId()));
        return ResponseResult.success(userInfoVo);
    }

    @PostMapping("logout")
    public ResponseResult logout() {
        MyPrincipal principal = (MyPrincipal) SecurityUtils.getSubject().getPrincipal();
        tokenService.deleteById(principal.getId());
        return ResponseResult.success();
    }


}

