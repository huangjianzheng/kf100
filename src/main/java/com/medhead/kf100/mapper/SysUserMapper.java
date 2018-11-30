package com.medhead.kf100.mapper;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectManagerUserListByPage(Page page, @Param("keywords") String keywords);

    List<SysUser> selectAllUserListByPage(Page page,SysUser sysUser);

    SysUser getUserCompanyById(@Param("id") Integer id);

    SysUser getUserInfo(@Param("id") Integer id);

}
