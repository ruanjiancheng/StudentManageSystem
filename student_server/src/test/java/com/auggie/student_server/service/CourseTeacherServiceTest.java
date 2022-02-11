package com.auggie.student_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseTeacherServiceTest {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @Test
    public void test() {
        System.out.println(courseTeacherService.findMyCourse(4, "22-spring"));
    }

}