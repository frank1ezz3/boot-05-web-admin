package com.atguigu.admin.service;

import com.atguigu.admin.bean.City;

public interface CityService {
    public City getById(Long id);
    //    City getById(Long id);
//
    public void saveCity(City city);
}
