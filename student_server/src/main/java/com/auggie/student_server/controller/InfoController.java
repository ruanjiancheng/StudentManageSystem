package com.auggie.student_server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: auggie
 * @Date: 2022/2/10 14:50
 * @Description: InfoController
 * @Version 1.0.0
 */

@RestController
@RequestMapping("/info")
@CrossOrigin("*")
public class InfoController {
    private final String CURRENT_TERM = "22-春季学期";
    private final boolean FORBID_COURSE_SELECTION = false;

    @RequestMapping("/getCurrentTerm")
    public String getCurrentTerm() {
        return CURRENT_TERM;
    }

    @RequestMapping("/getForbidCourseSelection")
    public boolean getForbidCourseSelection() {
        return FORBID_COURSE_SELECTION;
    }
}
