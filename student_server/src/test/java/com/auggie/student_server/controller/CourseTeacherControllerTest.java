package com.auggie.student_server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
class CourseTeacherControllerTest {

    @Autowired
    private CourseTeacherController controller;

    @Test
    public void test() {
        System.out.println(controller.findMyCourse(4, "22-spring"));
    }

}