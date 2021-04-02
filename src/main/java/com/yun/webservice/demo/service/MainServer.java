package com.yun.webservice.demo.service;

import javax.xml.ws.Endpoint;

/**
 * @author yunlong.zhang
 * @date 2020/6/10 22:25
 */
public class MainServer {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
    }
}
