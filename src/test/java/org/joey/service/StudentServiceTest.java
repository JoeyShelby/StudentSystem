package org.joey.service;

import org.joey.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: JoeyShelby
 * @date: 2022-09-13 09:54
 */
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testGetStudentsDoNotHaveGrade() {
        List<Student> studentsDoNotHaveGrade = studentService.getStudentsDoNotHaveGrade();
        for (Student student : studentsDoNotHaveGrade) {
            System.out.println(student.getName());
        }
    }

    @Test
    public void testGetAllStudents() {
        for (Student allStudent : studentService.getAllStudents()) {
            System.out.println(allStudent.getName());
        }
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setNum(1);
        student.setName("Joey");
        student.setSex("female");
        student.setBirth("2001-09-23");
        System.out.println(studentService.updateStudent(student));
    }
}
