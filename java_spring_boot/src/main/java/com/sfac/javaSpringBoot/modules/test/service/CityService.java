package com.sfac.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.entity.Country;

import java.util.List;

public interface CityService {
    List<City> getcitiesByCountryId(int countryId);

    // 分页
    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

    //插入数据
    Result<City> insertCity(City city);

    //更新数据
    Result<City> updateCity(City city);

    //删除数据，删除以后都没有了所以返回object对象
    Result<Object> deleteCity(int cityId);


}
