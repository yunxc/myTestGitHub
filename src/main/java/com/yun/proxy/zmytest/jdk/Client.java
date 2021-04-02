package com.yun.proxy.zmytest.jdk;

import com.sun.deploy.net.proxy.ProxyUtils;
import com.yun.proxy.zmytest.service.UserService;
import com.yun.proxy.zmytest.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/4/2 16:07
 */
public class Client {

    public static void main(String[] args) {
        //System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserService UserService = new UserServiceImpl();
        ClassLoader classLoader = UserService.getClass().getClassLoader();
        Class<?>[] interfaces = UserService.getClass().getInterfaces();
        InvocationHandler invocationHandler = new LogHandler(UserService);
        UserService u = (UserService)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        List<String> select = u.select();
        int update = u.update();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        // ProxyUtils.generateClassFile(UserService.getClass(), "UserServiceProxy");
    }

}
