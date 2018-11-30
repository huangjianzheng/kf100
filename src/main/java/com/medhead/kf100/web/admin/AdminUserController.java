package com.medhead.kf100.web.admin;


import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.model.dto.SysUserDto;
import com.medhead.kf100.service.SysUserService;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ming
 * @since 2018-11-26
 */
@RestController
@RequestMapping("api/admin/user")
@ApiIgnore
public class AdminUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("list")
    public ResponseResult selectSysUserListByUser(SysUserDto sysUserDto) {
        if (sysUserDto == null) {
            return ResponseResult.error("参数不能为空");
        }
        return ResponseResult.success(sysUserService.selectSysUserListByUser(sysUserDto));
    }

    @PostMapping("getUserCompany")
    public ResponseResult getSysUserCompanyInfo(Integer userId) {
        if (userId == null) {
            return ResponseResult.error("用户ID不能为空");
        }
        return ResponseResult.success(sysUserService.getSysUserCompany(userId));
    }

    @PostMapping("getUser")
    public ResponseResult getSysUserInfo(Integer userId) {
        if (userId == null) {
            return ResponseResult.error("用户ID不能为空");
        }
        SysUser sysUserInfo = sysUserService.getSysUserInfo(userId);
        return ResponseResult.success(sysUserInfo);
    }

}

