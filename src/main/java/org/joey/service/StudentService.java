package org.joey.service;

import org.joey.mapper.StudentMapper;
import org.joey.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: JoeyShelby
 * @date: 2022-09-13 09:51
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getStudentsDoNotHaveGrade() {
        return studentMapper.selectStudentsDoNotHaveGrade();
    }

    public List<Student> getAllStudents() {
        return studentMapper.selectByExample(null);
    }

    // 修改学生信息
    public Integer updateStudent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    // 根据学号删除学生
    public Integer removeStudent(Integer num) {
        return studentMapper.deleteByPrimaryKey(num);
    }

    // 添加新学生信息
    public Integer addStudent(Student student) {
        return studentMapper.insertSelective(student);
    }
}
