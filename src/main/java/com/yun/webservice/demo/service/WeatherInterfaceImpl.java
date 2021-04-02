package com.yun.webservice.demo.service;

import javax.jws.WebService;

/**
 * @author yunlong.zhang
 * @date 2020/6/10 22:24
 */
@WebService
public class WeatherInterfaceImpl implements WeatherInterface {

    @Override
    public String queryWeather(String cityName) {
        System.out.println("获取城市名"+cityName);
        String weather="暴雨";
        return weather;
    }
}
