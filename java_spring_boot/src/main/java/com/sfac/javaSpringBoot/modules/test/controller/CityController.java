package com.sfac.javaSpringBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;
    /*
    * 127.0.0.1/api/cities/522
    * */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId){
        return cityService.getcitiesByCountryId(countryId);
    }

    /**
     * 127.0.0.1/api/cities/522 --- post
     * {"currentPage":"1","pageSize":"5"}
     */
//    查询的时候有多个参数的用post请求处理,consumes = "application页面提交到控制器的数据格式
//    produce是从控制器提交出去的
    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }
}
