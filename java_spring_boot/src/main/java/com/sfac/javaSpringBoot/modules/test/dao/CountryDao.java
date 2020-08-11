package com.sfac.javaSpringBoot.modules.test.dao;

import com.sfac.javaSpringBoot.modules.test.entity.Country;

import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.util.List;

//dao层的注解
@Repository
//mybatis的注解
@Mapper
public interface CountryDao {
// order by 排序的时候，字段需要前端传递回来，用美元符号进行拼接，用#号的时候会有一个引号拼接进来
    @Select("select * from m_country where country_id=#{countryId}")
    /*组合查询*/
    @Results(id = "countryResults",value={
//            本身的id被占用了，所以这里再进行一次映射， 而这个映射到了city，@Result(column = "country_id",property = "cities",
            @Result(column = "country_id",property = "countryId"),
            @Result(column = "country_id",property = "cities",
                    javaType= List.class,
//                    下面是通过country_id作为参数调用下面方法，把结果装到cities里面去
                    many = @Many(select="com.sfac.javaSpringBoot.modules.test.dao.CityDao.getcitiesByCountryId")
            )
    })
    Country getCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name = #{countryName}")
    @ResultMap(value = "countryResults")
    Country getCountryByCountryName(String countryName);
}
