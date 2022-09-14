package org.joey.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.joey.pojo.Grade;
import org.joey.pojo.Msg;
import org.joey.pojo.StudentGrade;
import org.joey.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
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
        gradeService.updateStuGrade(studentGrade);
        return Msg.success();
    }

    // 跳转到添加成绩页面
    @GetMapping("/addGrade")
    public ModelAndView toAddGrade(ModelAndView mv) {
        mv.setViewName("addGrade");
        return mv;
    }


    //添加学生成绩
    @PostMapping("/addGrade")
    @ResponseBody
    public Msg addStuGrade(StudentGrade studentGrade) {
        System.out.println(studentGrade.getName());
        gradeService.insertStuGrade(studentGrade);

        return Msg.success();
    }

    // 下载班级综合成绩报表
    @GetMapping("/downClassGrade")
    public ResponseEntity<byte[]> downClassGrade(HttpSession session) throws IOException {
        List<Grade> grade = gradeService.getGrade();

        String fileName = ResourceUtils.getURL("classpath:").getPath()+"\\static\\excel\\classGrade.xlsx";

        EasyExcel.write(fileName, Grade.class)
                .sheet("班级综合成绩")
                .doWrite(grade);


        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //创建输入流
        InputStream is = new FileInputStream(fileName);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=classGrade.xlsx");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    // 下载学生个人成绩表
    @GetMapping("/downStudentGrade")
    public ResponseEntity<byte[]> downStudentGrade(HttpSession session) throws IOException {
        List<StudentGrade> allStuGrades = gradeService.getAllStuGrades();

        String fileName = ResourceUtils.getURL("classpath:").getPath()+"\\static\\excel\\studentGrade.xlsx";

        EasyExcel.write(fileName, StudentGrade.class)
                .sheet("班级个人成绩")
                .doWrite(allStuGrades);


        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //创建输入流
        InputStream is = new FileInputStream(fileName);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=studentGrade.xlsx");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }



    // 根据学号获取学生成绩
    @GetMapping("studentGrade/{num}")
    @ResponseBody
    public Msg getStudentGradeByNum(@PathVariable Integer num) {
        Msg success = Msg.success();
        HashMap<String, Object> extend = new HashMap<>();

        StudentGrade studentGrade = gradeService.getStudentGradeByNum(num);

        if(studentGrade == null) return Msg.fail();

        extend.put("studentGrade", studentGrade);
        success.setExtend(extend);

        return success;
    }

    // 获得所有有成绩的学生的成绩的集合
    @GetMapping("/stuGradeNums")
    @ResponseBody
    public Msg getStudentNumList() {
        ArrayList<Integer> nums = gradeService.getStudentNumList();

        if (nums == null) {
            return Msg.fail();
        }

        HashMap<String, Object> extend = new HashMap<>();

        extend.put("nums", nums);
        Msg success = Msg.success();
        success.setExtend(extend);
        return success;
    }

}
