package com.yun.proxy.dynamic.cglib;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/1/30 22:13
 */
public class LogInterceptor2 implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(object, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println(String.format("LogInterceptor2--log2 start time [%s] ", new Date()));
    }
    private void after() {
        System.out.println(String.format("LogInterceptor2--log2 end time [%s] ", new Date()));
    }

}
