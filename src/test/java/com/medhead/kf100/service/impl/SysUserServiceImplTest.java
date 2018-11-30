package com.medhead.kf100.service.impl;

import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.model.dto.SysUserDto;
import com.medhead.kf100.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void selectSysUserListByUser() {
        SysUserDto sysUserDto =new SysUserDto();
        sysUserDto.setCurrent(1);
        sysUserDto.setSize(6);
        sysUserDto.setEmail("88888@qq.com");
        sysUserService.selectSysUserListByUser(sysUserDto);
    }

    @Test
    public void getSysUser(){
        SysUser sysUser =sysUserService.getSysUserCompany(13);
        System.out.println(sysUser.toString());
    }
}