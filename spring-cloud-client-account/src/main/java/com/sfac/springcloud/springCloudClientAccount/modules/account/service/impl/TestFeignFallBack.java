package com.sfac.springcloud.springCloudClientAccount.modules.account.service.impl;

import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.sfac.springcloud.springCloudClientAccount.modules.account.service.TestFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TestFeignFallBack implements TestFeignClient {
    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return new ArrayList<City>();
    }
}
