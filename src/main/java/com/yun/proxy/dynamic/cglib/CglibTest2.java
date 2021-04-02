package com.yun.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author Administrator
 * @date 2021/1/31 17:19
 */
public class CglibTest2 {

    public static void main(String[] args) {
        LogInterceptor1 logInterceptor1 = new LogInterceptor1();
        LogInterceptor2 logInterceptor2 = new LogInterceptor2();
        Enhancer enhancer = new Enhancer();
        // 设置超类，cglib是通过继承来实现的
        enhancer.setSuperclass(UserDao.class);
        // 设置多个拦截器，NoOp.INSTANCE是一个空拦截器，不做任何处理
        enhancer.setCallbacks(new Callback[]{logInterceptor1, logInterceptor2, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());

        // 创建代理类
        UserDao proxy = (UserDao) enhancer.create();
        proxy.select();
        proxy.update();
    }

}
