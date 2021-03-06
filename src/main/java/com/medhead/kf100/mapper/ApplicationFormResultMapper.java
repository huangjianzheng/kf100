package com.medhead.kf100.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.model.ApplicationFormResult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
public interface ApplicationFormResultMapper extends BaseMapper<ApplicationFormResult> {
    
    
    List<ApplicationFormResult> selectApplicationFormResultPage(Page<ApplicationFormResult> page, @Param("ew") Wrapper<ApplicationFormResult> ew);
}
