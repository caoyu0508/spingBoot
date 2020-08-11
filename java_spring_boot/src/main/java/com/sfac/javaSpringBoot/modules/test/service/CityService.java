package com.sfac.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getcitiesByCountryId(int countryId);

    // 分页
    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);
}
