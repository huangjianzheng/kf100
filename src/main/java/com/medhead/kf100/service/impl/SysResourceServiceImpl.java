package com.medhead.kf100.service.impl;

import com.medhead.kf100.model.SysResource;
import com.medhead.kf100.mapper.SysResourceMapper;
import com.medhead.kf100.service.SysResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Override
    public List<String> findAllPermissionByUserId(Long id) {
        return baseMapper.findAllPermissionByUserId(id);
    }
}
