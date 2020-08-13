package com.sfac.javaSpringBoot.modules.test.repository;

import com.sfac.javaSpringBoot.modules.test.entity.Card;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    /*===========================Repository层包装好的属性查询=======================================*/
    //根据姓名进行查询
    List<Student> findByStudentName(String studentName);

    //模糊查询
    List<Student> findByStudentNameLike(String studentName);

    //模糊查询并只显示前两个
    List<Student> findTop2ByStudentNameLike(String studentName);

    /*=====================================Repository层自定义方法查询===============================*/
//    @Query(value = "select s from Student s where s.studentName=?1 and s.studentCard.cardId=?2")
//    @Query(value = "select s from Student s where s.studentName = :studentName " +
//           "and s.studentCard.cardId = :cardId")
    //nativeQuery = true,设置原生sql语句
    @Query(nativeQuery = true,
            value = "select * from h_student where student_name = :studentName " +
                    "and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);
}

