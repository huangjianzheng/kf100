package com.medhead.kf100.service.impl;

import com.medhead.kf100.model.Course;
import com.medhead.kf100.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

//    @Test
//    public void getCourseList() {
//        List<> =courseService.getCourseList(1,2);
//    }

    @Test
    public void updateById(){
        Course course =new Course();
        course.setId((long)113);
        course.setStatus(0);
        Boolean aBoolean=courseService.updateById(course);
        System.out.println(aBoolean);
    }
}