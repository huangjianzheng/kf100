package com.medhead.kf100.service.impl;

import com.medhead.kf100.model.SysRole;
import com.medhead.kf100.mapper.SysRoleMapper;
import com.medhead.kf100.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<String> findAllRoleTypeByUserId(Long userId) {
        return baseMapper.findAllRoleTypeByUserId(userId);
    }
}
