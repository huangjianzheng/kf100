package com.medhead.kf100.mapper;

import com.medhead.kf100.model.SysResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author ming
 * @since 2018-11-26
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    List<String> findAllPermissionByUserId(Long userId);
}
