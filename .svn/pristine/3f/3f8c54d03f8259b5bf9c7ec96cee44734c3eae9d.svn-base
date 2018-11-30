package com.medhead.kf100.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.model.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.medhead.kf100.model.dto.SysUserDto;
import com.medhead.kf100.model.vo.AdminManagerVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
public interface SysUserService extends IService<SysUser> {

    Page<SysUser> selectManagerUserListByPage(Page<SysUser> page, String keywords);

    void newManagerUser(String userName, String account, String passWord, Long roleId);

    void updateManagerUser(Long userId, String userName, String passWord, Long roleId);

    PageEntity<SysUser> selectSysUserListByUser(SysUserDto sysUserDto);

    SysUser getSysUserCompany(Integer userId);

    SysUser getSysUserInfo(Integer userId);
}
