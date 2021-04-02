package com.yun.proxy.zmytest.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @date 2021/4/2 15:21
 */
public class LogHandler implements InvocationHandler {

    Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(target, args);
        after();
        return invoke;
    }

    private void before(){
        String name = Thread.currentThread().getName();
        System.out.println(name + "==============before============");
    }

    private void after(){
        String name = Thread.currentThread().getName();
        System.out.println(name + "==============after============");
    }
}
