package com.auggie.student_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void test() {

        HashMap<String, String> map = new HashMap<>();

        map.put("tname", "a");
        map.put("fuzzy", "1");


        System.out.println(teacherService.findBySearch(map));
    }
}