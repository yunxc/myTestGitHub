package com.yun.proxy.zmytest.service;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/1/30 20:18
 */
public class UserServiceI{

    public List<String> select() {
        System.out.println("查询 selectById");
        List<String> li = new ArrayList();
        li.add("hello");
        return li;
    }
    
    public int update() {
        System.out.println("更新 update");
        return 666;
    }

    @Override
    public String toString() {
        System.out.println("=======toString======");
        return "UserServiceImpl{}";
    }

    @Override
    public int hashCode() {
        System.out.println("=======hashCode======");
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("=======equals======");
        return super.equals(obj);
    }
}
