package com.yun.proxy.staticc;

/**
 * @author Administrator
 * @date 2021/1/30 20:18
 */
public class Main {

    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();
        UserService proxy = new UserServiceProxy(userServiceImpl);

        proxy.select();
        proxy.update();

    }

}
