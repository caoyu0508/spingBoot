package com.safc.springcloud.springCoudClientTest.modules.test.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safc.springcloud.springCoudClientTest.modules.common.vo.Result;
import com.safc.springcloud.springCoudClientTest.modules.common.vo.SearchVo;
import com.safc.springcloud.springCoudClientTest.modules.test.dao.CityDao;
import com.safc.springcloud.springCoudClientTest.modules.test.entity.City;
import com.safc.springcloud.springCoudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getcitiesByCountryId(int countryId) {
        return Optional.ofNullable(cityDao.getcitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        //初始化，如果前端没有传值过来，给它一个默认值
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(
                Optional.ofNullable(cityDao.getcitiesByCountryId(countryId))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(
                Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Insert success.", city);
    }

    @Override
    //事务设置，当遇到算数异常的时候不发生回滚，继续运行
    @Transactional(noRollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
//        int i=1/0;
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Update success",city);
    }

    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,
                "Delete success");
    }


}
