package com.yun.proxy.dynamic.cglib;



import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/1/30 21:35
 */
public class LogInterceptor1 implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        // 注意这里是调用 invokeSuper 而不是 invoke，否则死循环，methodProxy.invokesuper执行的是原始类的方法，method.invoke执行的是子类的方法
        Object result = methodProxy.invokeSuper(object, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println(String.format("LogInterceptor1--log start time [%s] ", new Date()));
    }
    private void after() {
        System.out.println(String.format("LogInterceptor1--log end time [%s] ", new Date()));
    }

}
