package org.joey.service;

import org.joey.pojo.Grade;
import org.joey.pojo.StudentGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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
        service.updateStuGrade(11, "王小波",1.0,1.0,1.0,1.0);
    }

}
