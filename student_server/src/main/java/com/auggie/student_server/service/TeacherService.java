package com.auggie.student_server.service;

import com.auggie.student_server.entity.Student;
import com.auggie.student_server.entity.Teacher;
import com.auggie.student_server.mapper.StudentMapper;
import com.auggie.student_server.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: auggie
 * @Date: 2022/2/9 10:55
 * @Description: TeacherService
 * @Version 1.0.0
 */

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> findBySearch(Map<String, String> map) {
        Integer tid = null;
        String tname = null;
        Integer fuzzy = null;
        if (map.containsKey("tid")) {
            try {
                tid = Integer.parseInt(map.get("tid"));
            }
            catch (Exception e) {
            }
        }
        if (map.containsKey("tname")) {
            tname = map.get("tname");
        }
        if (map.containsKey("fuzzy")) {
            fuzzy = map.get("fuzzy").equals("true") ? 1 : 0;
        }
        System.out.println(map);
        System.out.println("查询类型：" + tid  + ", " + tname + ", " + fuzzy);
        return teacherMapper.findBySearch(tid, tname, fuzzy);
    }

    public List<Teacher> findByPage(Integer num, Integer size) {
        // num：第几页，size：一页多大
        // num：从零开始
        List<Teacher> teacherList = teacherMapper.findAll();
        ArrayList<Teacher> list = new ArrayList<Teacher>();

        int start = size * num;
        int end = size * (num + 1);
        int sz = teacherList.size();

        for (int i = start; i < end && i < sz; i++) {
            list.add(teacherList.get(i));
        }

        return list;
    }

    public Integer getLength() {
        return teacherMapper.findAll().size();
    }

    public Teacher findById(Integer tid) {
        return teacherMapper.findById(tid);
    }

    public boolean updateById(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    public boolean save(Teacher teacher) {
        return teacherMapper.save(teacher);
    }

    public boolean deleteById(Integer tid) {
        return teacherMapper.deleteById(tid);
    }
}
