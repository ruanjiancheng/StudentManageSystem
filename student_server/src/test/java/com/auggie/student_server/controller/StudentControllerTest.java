package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @Test
    public void test() {
        Student student = new Student();
        student.setSname("李玉民");
        student.setPassword("123");

        System.out.println(studentController.addStudent(student));
    }
}