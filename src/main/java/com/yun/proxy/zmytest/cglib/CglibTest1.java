package com.yun.proxy.zmytest.cglib;

import com.yun.proxy.zmytest.service.UserServiceI;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author Administrator
 * @date 2021/4/2 17:12
 */
public class CglibTest1 {
    public static void main(String[] args) {
        // 生成class类的路径
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E://tmp");
        LogInterceptor1 logInterceptor1 = new LogInterceptor1();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceI.class);
        enhancer.setCallback(logInterceptor1);

        UserServiceI u = (UserServiceI)enhancer.create();
        u.select();
        u.update();
    }
}
