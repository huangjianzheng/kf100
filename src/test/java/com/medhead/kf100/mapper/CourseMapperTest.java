package com.medhead.kf100.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

//    @Test
//    public void getCourseList() {
//        List<Course>list =courseMapper.getCourseList(Page page);
//        System.out.println(list.toString());
//    }
}