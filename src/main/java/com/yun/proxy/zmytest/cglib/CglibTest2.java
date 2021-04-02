package com.yun.proxy.zmytest.cglib;

import com.yun.proxy.zmytest.service.UserServiceI;
import com.yun.proxy.zmytest.service.UserServiceImpl;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author Administrator
 * @date 2021/4/2 18:45
 */
public class CglibTest2 {

    public static void main(String[] args) {
        LogInterceptor1 logInterceptor1 = new LogInterceptor1();
        LogInterceptor2 logInterceptor2 = new LogInterceptor2();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallbacks(new Callback[]{logInterceptor1, logInterceptor2, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new UserServiceFilter());
        // 创建代理类
        UserServiceImpl proxy = (UserServiceImpl) enhancer.create();
        proxy.select();
        proxy.update();
        proxy.hashCode();


    }

}
