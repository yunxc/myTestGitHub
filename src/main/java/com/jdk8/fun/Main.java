package com.jdk8.fun;

/**
 * @author Administrator
 * @date 2021/6/1 9:58
 */
public class Main {

    public static void main(String[] args) {
        MyfunImpl1 m = new MyfunImpl1();
        funTest(3, m);
        funTest(3, a -> a + 5);
    }

    public static void funTest(int funVale, Myfun fun){
        int a = 2;
        int i = fun.funTest(funVale);
        System.out.println("============:" + (a+i));
    }

}
