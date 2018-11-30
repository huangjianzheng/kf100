package com.medhead.kf100.web.admin;


import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.common.util.CommonUtil;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ming
 * @since 2018-11-26
 */
@RestController
@RequestMapping("api/admin/manager")
@ApiIgnore
public class AdminManagerController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("list")
    public ResponseResult<PageEntity<SysUser>> getManagerList(String keywords, Integer current, Integer pageSize) {
        Page<SysUser> page = new Page<>(current,pageSize);
        return ResponseResult.success(CommonUtil.pageToPageEntity(sysUserService.selectManagerUserListByPage(page, keywords)));
    }

    @PostMapping("new")
    public ResponseResult newManagerUser(String userName,String account,String passWord,Long roleId){
        sysUserService.newManagerUser(userName,account,passWord,roleId);
        return  ResponseResult.success();
    }
    @PostMapping("update")
    public ResponseResult updateManagerUser(Long userId,String userName,String passWord,Long roleId){
        sysUserService.updateManagerUser(userId,userName,passWord,roleId);
        return  ResponseResult.success();
    }


}

