package com.medhead.kf100.mapper;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void selectAllUserListByPage() {
        Page<SysUser> page =new Page(1,5);
        SysUser sysUser =new SysUser();
        //sysUser.setEmail("88888@qq.com");
        //sysUser.setUserName("黄主任");
        sysUser.setAccount("zhujiao");
        List<SysUser> list =sysUserMapper.selectAllUserListByPage(page,sysUser);
        System.out.println(list.toString());
    }

    @Test
    public void getUser(){
        SysUser sysUser=sysUserMapper.getUserInfo(13);
        System.out.println(sysUser.toString());
    }
}