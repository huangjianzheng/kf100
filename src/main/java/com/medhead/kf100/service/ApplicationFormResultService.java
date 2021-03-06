package com.medhead.kf100.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.medhead.kf100.model.ApplicationFormResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
public interface ApplicationFormResultService extends IService<ApplicationFormResult> {
    Page<ApplicationFormResult> selectApplicationFormResultPage(Page<ApplicationFormResult> page, @Param("ew") Wrapper<ApplicationFormResult> ew);
}
