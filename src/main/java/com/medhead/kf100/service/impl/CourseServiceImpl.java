package com.medhead.kf100.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.common.util.CommonUtil;
import com.medhead.kf100.model.Course;
import com.medhead.kf100.mapper.CourseMapper;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.service.CourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     *  获取所有课程信息
     * @param current
     * @param size
     * @return
     */
    @Override
    public PageEntity<Course> getCourseList(Integer current,Integer size) {
        Page pageEntity =new Page(current,size);
        List<Course> list =courseMapper.getCourseList(pageEntity);
        return CommonUtil.pageToPageEntity(pageEntity.setRecords(list));
    }

    /**
     *  修改课程状态
     * @param Id
     */
    @Override
    public void updateCourseStatusByid(Integer Id,Integer status) {
        Course course = new Course();
        course.setId((long) Id);
        course.setStatus(status);
        courseMapper.updateById(course);
    }
}
