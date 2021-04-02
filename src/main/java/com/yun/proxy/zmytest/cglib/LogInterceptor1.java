package com.yun.proxy.zmytest.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @date 2021/4/2 17:13
 */
public class LogInterceptor1 implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object o = proxy.invokeSuper(obj, args);
        after();
        return o;
    }

    private void before(){
        String name = Thread.currentThread().getName();
        System.out.println(name + "LogInterceptor1==============before============");
    }

    private void after(){
        String name = Thread.currentThread().getName();
        System.out.println(name + "LogInterceptor1==============after============");
    }

}
