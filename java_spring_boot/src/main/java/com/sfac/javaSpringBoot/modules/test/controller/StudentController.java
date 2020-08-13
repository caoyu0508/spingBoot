package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentSevice studentSevice;
    /*=========================Repository层继承JpaRepository父接口的方法的实现==========================*/

    /**
     * 127.0.0.1/api/student ---- post
     * {"studentName":"hujiang1","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentSevice.insertStudent(student);
    }

    /**
     * @param studentId 127.0.0.1/api/student/1-----get
     */
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId) {
        return studentSevice.getStudentByStudentId(studentId);
    }


    /**
     * @param searchVo 127.0.0.1/api/studemts
     *                 {"currentPage":"1","pageSize":"5","keyWord":"hu","orderBy":"studentId","sort":"asc"}
     */
    @PostMapping(value = "/students", consumes = "application/json")
    public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
        return studentSevice.getStudentsBySearchVo(searchVo);
    }

    /*测试方法开始*/

    /**
     * @return findAll（）无参方法
     * 127.0.0.1/api/students
     */
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentSevice.getStudents();
    }

    /**
     * findAll()有sort参数的方法测试
     * 127.0.0.1/api/student
     */
    @GetMapping("/student")
    public List<Student> getStudent() {
        return studentSevice.getStudent();
    }
    /*=====================================Repository层包装好的属性查询=======================================*/

    /**
     * required = false 代表它并不是必需的
     * defaultValue = "1" 给它一个默认值
     * 127.0.0.1/api/studentByParams?studentName=hu 属性查询
     * 127.0.0.1/api/studentByParams?studentName=laohu&carId=1 自定义方法查询
     */
    @GetMapping("/studentByParams")
    public List<Student> getStudentsByParams(
            @RequestParam String studentName,
            @RequestParam(required = false, defaultValue = "0") Integer carId) {
        return studentSevice.getStudentByStudentName(studentName,carId);
    }


    /*=====================================Repository层自定义方法查询=======================================*/

}
