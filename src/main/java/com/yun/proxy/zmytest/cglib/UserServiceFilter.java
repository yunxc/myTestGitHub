package com.yun.proxy.zmytest.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @date 2021/4/2 18:50
 */
public class UserServiceFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        String name = method.getName();
        if("select".equals(name)){
            return 0;
        } else if("update".equals(name)){
            return 1;
        } else {
            return 2;
        }
    }
}
