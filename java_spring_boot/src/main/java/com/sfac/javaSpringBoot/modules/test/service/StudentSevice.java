package com.sfac.javaSpringBoot.modules.test.service;


import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentSevice {
/*=========================Repository层继承JpaRepository父接口的方法的实现==========================*/
    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    Page<Student> getStudentsBySearchVo(SearchVo searchVo);

    /*jpa分页查询分布测试方法*/
    List<Student> getStudents();

    //有参sort
    List<Student> getStudent();
    /*测试结束*/
 /*=====================================Repository层包装好的属性查询=======================================*/
    //根据学生姓名查询
    List<Student> getStudentByStudentName(String studentNameint,int carId);
/*=====================================Repository层自定义方法查询=======================================*/
}
