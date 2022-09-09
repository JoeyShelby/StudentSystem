package org.joey.controller;

import org.joey.pojo.Msg;
import org.joey.pojo.StudentGrade;
import org.joey.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: JoeyShelby
 * @date: 2022-09-09 10:24
 */
@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 返回全部学生成绩
     */
    @GetMapping("/stuGrades")
    @ResponseBody
    public Msg getAllStuGrades() {
        List<StudentGrade> allStuGrades = gradeService.getAllStuGrades();
        Msg msg = Msg.success();
        Map<String, Object> map = new HashMap<>();
        map.put("stuGrades", allStuGrades);
        msg.setExtend(map);

        return msg;
    }

    /**
     * 根据学号删除学生成绩
     */
    @DeleteMapping("/stuGrade/{num}")
    @ResponseBody
    public Msg removeStuGrade(@PathVariable Integer num) {
        if(num == null) {
            return Msg.fail();
        }
        gradeService.removeStuGrade(num);
        return Msg.success();
    }

    /**
     * 删除
     */
    @PutMapping("/stuGrade")
    @ResponseBody
    public Msg updateStuGrade(StudentGrade studentGrade) {
        System.out.println(studentGrade.getNum());
        System.out.println(studentGrade.getName());
        System.out.println(studentGrade.getMath());
        System.out.println(studentGrade.getJava());
        System.out.println(studentGrade.getEnglish());
        System.out.println(studentGrade.getPe());

        return Msg.success();
    }
}
