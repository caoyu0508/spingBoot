package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

//不用在每个方法上写responsebody
@RestController
@RequestMapping("api")
public class CountryController {
    @Autowired
    private CountryService countryService;
    /*
     * 127.0.0.1/api/country/522
     * */
    @GetMapping("/country/{countryId}")
//    用这个注解就可以得到{countryId}里的值
    public Country getCountryByCountryId(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

    /*
    * 127.0.0.1/api/country?countryName=China
    * */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
    }


    /**
     * 127.0.0.1/api/redis/country/522--------get
     * @return
     */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId){
        return countryService.mograteCountryByRedis(countryId);
    }
}
