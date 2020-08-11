package com.sfac.javaSpringBoot.modules.test.dao;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface CityDao {
    @Select("select * from m_city where country_id=#{country_id}")
    List<City> getcitiesByCountryId(int countryId);
}
