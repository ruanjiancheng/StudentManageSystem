package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void test() {
        Student student = new Student();
        student.setSid(14);
        student.setSname("薇尔莉特");
        student.setPassword("123");



        System.out.println(studentMapper.findById(student.getSid()));
    }
}