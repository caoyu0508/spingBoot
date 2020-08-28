package com.safc.springcloud.springCoudClientTest.modules.test.dao;



import com.safc.springcloud.springCoudClientTest.modules.common.vo.SearchVo;
import com.safc.springcloud.springCoudClientTest.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {
    @Select("select * from m_city where country_id=#{country_id}")
    //脚本多条件查询
    List<City> getcitiesByCountryId(int countryId);
    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    /*
    * 增删改操作返回的是int类型，数字代表执行影响的条数，这里不需要返回，所以用void
    * */
    //插入数据
    @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) " +
            "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
    /*Options注解 将刚刚插入数据的id拿出来做一个映射，当有city_id数据传来，会映射到city对象中
    useGeneratedKeys：使用生成的主键。
    keyColumn：对应到表的每一列
    keyProperty：对应到每一个属性
    */
    @Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
    void insertCity(City city);

    //更新数据
    @Update("update m_city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);

    //删除数据
    @Delete("delete from m_city where city_id=#{cityId}")
    void deleteCity(int cityId);
}
