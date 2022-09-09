package org.joey.service;

import org.joey.mapper.GradeMapper;
import org.joey.mapper.StudentGradeMapper;
import org.joey.pojo.Grade;
import org.joey.pojo.GradeExample;
import org.joey.pojo.StudentGrade;
import org.joey.pojo.StudentGradeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: JoeyShelby
 * @date: 2022-09-09 10:08
 */
@Service
public class GradeService {
    @Autowired
    private StudentGradeMapper studentGradeMapper;
    @Autowired
    private GradeMapper gradeMapper;

    // 获得全部学生成绩
    public List<StudentGrade> getAllStuGrades() {
        StudentGradeExample studentGradeExample = new StudentGradeExample();

        return studentGradeMapper.selectByExample(studentGradeExample);
    }

    // 根据学号删除学生成绩
    public Integer removeStuGrade(Integer num) {
        StudentGradeExample studentGradeExample = new StudentGradeExample();
        studentGradeExample.createCriteria().andNumEqualTo(num);

        return studentGradeMapper.deleteByExample(studentGradeExample);
    }

    // 修改学生成绩
    public Integer updateStuGrade(Integer num, String name, Double math, Double java, Double english, Double pe) {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setNum(num);
        studentGrade.setName(name);
        studentGrade.setMath(BigDecimal.valueOf(math));
        studentGrade.setJava(BigDecimal.valueOf(java));
        studentGrade.setEnglish(BigDecimal.valueOf(english));
        studentGrade.setPe(BigDecimal.valueOf(pe));
        studentGrade.setTotal(BigDecimal.valueOf(math+java+english+pe));
        studentGrade.setAverage(studentGrade.getTotal().divide(BigDecimal.valueOf(4)));
        return studentGradeMapper.updateByPrimaryKeySelective(studentGrade);
    }
}
