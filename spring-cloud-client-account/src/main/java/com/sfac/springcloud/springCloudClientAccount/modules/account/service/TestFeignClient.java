package com.sfac.springcloud.springCloudClientAccount.modules.account.service;

import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.sfac.springcloud.springCloudClientAccount.modules.account.service.impl.TestFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//指定实现类的class fallback = TestFeignFallBack.class
@FeignClient(value = "CLIENT-TEST",fallback = TestFeignFallBack.class)
@Primary
public interface TestFeignClient {
    @RequestMapping("/api/cities/{countryId}")
    List<City> getCitiesByCountryId(@PathVariable int countryId);
}
