package org.joey.service;

import org.joey.pojo.Grade;
import org.joey.pojo.Student;
import org.joey.pojo.StudentGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: JoeyShelby
 * @date: 2022-09-09 10:12
 */
@SpringBootTest
public class GradeServiceTest {

    @Autowired
    private GradeService service;

    @Test
    public void testGetAllGrades() {
        List<StudentGrade> allStuGrade = service.getAllStuGrades();
        for (StudentGrade studentGrade : allStuGrade) {
            System.out.println(studentGrade.getName());
        }
    }

    @Test
    public void testRemoveStuGrade() {
        Integer integer = service.removeStuGrade(42);
        System.out.println(integer);
    }

    @Test
    public void testUpdateStuGrade() {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setNum(1);
        studentGrade.setName("乔义");
        studentGrade.setMath(BigDecimal.valueOf(12));
        studentGrade.setJava(BigDecimal.valueOf(12));
        studentGrade.setEnglish(BigDecimal.valueOf(12));
        studentGrade.setPe(BigDecimal.valueOf(12));


        service.updateStuGrade(studentGrade);
    }

    @Test
    public void testInsertStuGrade() {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setNum(19);
        studentGrade.setName("小能");
        studentGrade.setMath(null);
        studentGrade.setJava(BigDecimal.valueOf(-1));
        studentGrade.setEnglish(BigDecimal.valueOf(-8.9));
        studentGrade.setPe(BigDecimal.valueOf(100));

        service.insertStuGrade(studentGrade);
    }

    @Test
    public void testGetGrade() {
        for (Grade grade : service.getGrade()) {
            System.out.println(grade.getTotal());
        }
    }

    @Test
    public void testGetStudentGradeByNum() {
        System.out.println(service.getStudentGradeByNum(2).getEnglish());
    }


    @Test
    public void testGetStudentNumList() {
        ArrayList<Integer> numList = service.getStudentNumList();

        numList.forEach(System.out::println);
    }
}
