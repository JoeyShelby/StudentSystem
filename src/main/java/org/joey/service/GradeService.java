package org.joey.service;

import org.joey.mapper.GradeMapper;
import org.joey.mapper.StudentGradeMapper;
import org.joey.pojo.Grade;
import org.joey.pojo.StudentGrade;
import org.joey.pojo.StudentGradeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public Integer updateStuGrade(StudentGrade studentGrade) {
        gradeFormat(studentGrade);

        return studentGradeMapper.updateByPrimaryKeySelective(studentGrade);
    }

    // 对从前端获取的学生成绩进行格式化
    private StudentGrade gradeFormat(StudentGrade studentGrade) {
        BigDecimal zero = new BigDecimal(0);

        if (studentGrade.getMath() == null || studentGrade.getMath().compareTo(zero) < 0) studentGrade.setMath(zero);
        if (studentGrade.getJava() == null || studentGrade.getJava().compareTo(zero) < 0) studentGrade.setJava(zero);
        if (studentGrade.getEnglish() == null || studentGrade.getEnglish().compareTo(zero) < 0) studentGrade.setEnglish(zero);
        if (studentGrade.getPe() == null || studentGrade.getPe().compareTo(zero) < 0) studentGrade.setPe(zero);


        studentGrade.setTotal(studentGrade.getMath().add(studentGrade.getJava()).add(studentGrade.getEnglish()).add(studentGrade.getPe()));
        studentGrade.setAverage(studentGrade.getTotal().divide(BigDecimal.valueOf(4)));

        return studentGrade;
    }

    // 添加学生成绩
    public Integer insertStuGrade(StudentGrade studentGrade) {
        gradeFormat(studentGrade);

        return studentGradeMapper.insert(studentGrade);
    }

    // 获得班级总成绩情况，获得总成绩前需要计算当前总成绩情况，不能直接下载
    public List<Grade> getGrade() {
        List<StudentGrade> allStuGrades = this.getAllStuGrades();
        Grade grade = new Grade();
        BigDecimal mathTotal = new BigDecimal(0);
        BigDecimal javaTotal = new BigDecimal(0);
        BigDecimal englishTotal = new BigDecimal(0);
        BigDecimal PETotal = new BigDecimal(0);
        BigDecimal mathAverage = new BigDecimal(0);
        BigDecimal javaAverage = new BigDecimal(0);
        BigDecimal englishAverage = new BigDecimal(0);
        BigDecimal PEAverage = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);
        BigDecimal totalAverage = new BigDecimal(0);

        for (StudentGrade stuGrade : allStuGrades) {
            mathTotal= mathTotal.add(stuGrade.getMath());
            javaTotal = javaTotal.add(stuGrade.getJava());
            englishTotal = englishTotal.add(stuGrade.getEnglish());
            PETotal = PETotal.add(stuGrade.getPe());

            total = total.add(stuGrade.getTotal());
            totalAverage = totalAverage.add(stuGrade.getAverage());
        }

        BigDecimal size = BigDecimal.valueOf(allStuGrades.size());

        mathAverage = mathTotal.divide(size);
        javaAverage = javaTotal.divide(size);
        englishAverage = mathTotal.divide(size);
        PEAverage = PETotal.divide(size);


        grade.setMathTotal(mathTotal);
        grade.setJavaTotal(javaTotal);
        grade.setEnglishTotal(englishTotal);
        grade.setPeTotal(PETotal);
        grade.setMathAverage(mathAverage);
        grade.setJavaAverage(javaAverage);
        grade.setEnglishAverage(englishAverage);
        grade.setPeAverage(PEAverage);
        grade.setTotal(total);
        grade.setTotalAverage(totalAverage);

        gradeMapper.updateByExampleSelective(grade, null);

        return gradeMapper.selectByExample(null);
    }


    // 根据学生学号查询成绩
    public StudentGrade getStudentGradeByNum(Integer num) {
        StudentGradeExample studentGradeExample = new StudentGradeExample();
        studentGradeExample.createCriteria().andNumEqualTo(num);
        List<StudentGrade> studentGrades = studentGradeMapper.selectByExample(studentGradeExample);

        if(studentGrades.size() > 0) {
            return studentGrades.get(0);
        }
        return null;
    }


    // 获得所有有成绩的学生的学号链表
    public ArrayList<Integer> getStudentNumList() {
        ArrayList<Integer> nums = new ArrayList<>();
        for (StudentGrade studentGrade : this.getAllStuGrades()) {
            nums.add(studentGrade.getNum());
        }

        return nums;
    }
}
