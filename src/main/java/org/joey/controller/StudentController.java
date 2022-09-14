package org.joey.controller;

import org.joey.pojo.Msg;
import org.joey.pojo.Student;
import org.joey.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: JoeyShelby
 * @date: 2022-09-13 09:57
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/stuNotHaveGrade")
    @ResponseBody
    public Msg studentsNotHaveGrade() {
        Msg success = Msg.success();
        Map<String, Object> extend = new HashMap<>();

        List<Student> studentsDoNotHaveGrade = studentService.getStudentsDoNotHaveGrade();
        extend.put("stuNotHaveGrade", studentsDoNotHaveGrade);

        success.setExtend(extend);
        return success;
    }

    @GetMapping("/student")
    public ModelAndView toStudents(ModelAndView mv) {
        mv.setViewName("students");
        return mv;
    }

    @GetMapping("/students")
    @ResponseBody
    public Msg getAllStudents() {
        Msg success = Msg.success();
        Map<String, Object> extend = new HashMap<>();

        List<Student> allStudents = studentService.getAllStudents();
        extend.put("students", allStudents);

        success.setExtend(extend);
        return success;
    }


    // 修改学生信息
    @PutMapping("student")
    @ResponseBody
    public Msg updateStudent(Student student) {
        if(studentService.updateStudent(student) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    // 根据学号删除学生
    @DeleteMapping("student/{num}")
    @ResponseBody
    public Msg deleteStudent(@PathVariable Integer num) {
      if (num == null) return Msg.fail();
      if(studentService.removeStudent(num) == 1) {
          return Msg.success();
      }
      return Msg.fail();
    }


    @GetMapping("addStudent")
    public ModelAndView toAddStudent(ModelAndView mv) {
        mv.setViewName("addStudent");
        return mv;
    }

    // 添加新学生
    @PostMapping("/student")
    @ResponseBody
    public Msg addStudent(Student student) {
        if(studentService.addStudent(student) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }
}
