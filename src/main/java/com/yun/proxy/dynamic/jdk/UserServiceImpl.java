package com.yun.proxy.dynamic.jdk;

/**
 * @author Administrator
 * @date 2021/1/30 20:18
 */
public class UserServiceImpl implements UserService {

    @Override
    public void select() {
        System.out.println("查询 selectById");
    }
    
    @Override
    public void update() {
        System.out.println("更新 update");
    }
}
