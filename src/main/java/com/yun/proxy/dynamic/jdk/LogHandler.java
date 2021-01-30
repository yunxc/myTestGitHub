package com.yun.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/1/30 21:26
 */
public class LogHandler implements InvocationHandler {

    Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        // 调用 target 的 method 方法
        Object result = method.invoke(target, args);
        after();
        // 返回方法的执行结果
        return result;
    }

    // 调用invoke方法之前执行
    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }
    // 调用invoke方法之后执行
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
