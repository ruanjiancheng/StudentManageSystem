package com.auggie.student_server.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseTeacherMapperTest {

    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Test
    public void test() {
        courseTeacherMapper.findMyCourse(4, "22-春季学期");
    }

}