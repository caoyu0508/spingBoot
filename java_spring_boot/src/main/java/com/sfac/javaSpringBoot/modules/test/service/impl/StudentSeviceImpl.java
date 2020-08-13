package com.sfac.javaSpringBoot.modules.test.service.impl;


import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.repository.StudentRepository;
import com.sfac.javaSpringBoot.modules.test.service.StudentSevice;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentSeviceImpl implements StudentSevice {
    @Autowired
    private StudentRepository studentRepository;
/*=========================Repository层继承JpaRepository父接口的方法的实现==========================*/
    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "Insert success",student);
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        //因为底层代码是option选择，所以用get去获取泛型所代表的对象
        return studentRepository.findById(studentId).get();
}

    @Override
    public Page<Student> getStudentsBySearchVo(SearchVo searchVo) {
        /*Direction是一个枚举，包含DESC、ASC，equalsIgnoreCase比较忽略大小写
        这里运用了三目运算进行判断，选择排序的方向*/
        Sort.Direction direction="desc".equalsIgnoreCase(searchVo.getSort())?
                Sort.Direction.DESC: Sort.Direction.ASC;
        Sort sort=new Sort(direction, StringUtils.isBlank(searchVo.getOrderBy())?
                "studentId":searchVo.getOrderBy());

        //Pageable接口，PageRequest是属于它的一个实现类，of方法有三个参数的、两个参数的
        //第一个参数起始页是从0开始的，所以这里要减一
        Pageable pageable=PageRequest.of(searchVo.getCurrentPage() - 1, searchVo.getPageSize(), sort);

        //创建一个example对象，一个查询的模板
        Student student=new Student();
        student.setStudentName(searchVo.getKeyWord());
        //match -> match.contains()函数式表达式 jdk1.8后的写法
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("studentName",match -> match.contains()).withIgnorePaths("studentId");
        Example<Student> example=Example.of(student,matcher);
        return studentRepository.findAll(example,pageable);
    }
    /*测试方法开始*/
    //无参数查询
    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    //有sort参数的查询
    public List<Student> getStudent() {
        Sort.Direction direction=Sort.Direction.ASC;
        Sort sort=new Sort(direction,"studentId");
        return studentRepository.findAll(sort);
    }
/*=====================================Repository层包装好的属性查询=======================================*/
    @Override
    public List<Student> getStudentByStudentName(String studentName,int carId) {
        if (carId>0){
            //测试自定义查询,该接口的方法是自定义的
            return studentRepository.getStudentsByParams(studentName,carId);
        }else {
            //测试的是属性查询
            return Optional
                    .ofNullable(studentRepository.findTop2ByStudentNameLike(
                            String.format("%s%s%s","%",studentName,"%")))
                    .orElse(Collections.emptyList());
        }
      /*  return Optional.
                ofNullable(studentRepository.findByStudentName(studentName))
                .orElse(Collections.emptyList());
*/
       /* return Optional
                .ofNullable(studentRepository.findByStudentNameLike(
                        String.format("%s%s%s", "%", studentName, "%")))
                .orElse(Collections.emptyList());*/

    }

/*=====================================Repository层自定义方法查询=======================================*/
}
