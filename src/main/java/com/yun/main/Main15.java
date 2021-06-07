package com.yun.main;

/**
 * @author Administrator
 * @date 2021/5/20 19:34
 */
public class Main15 {

    public static void main(String[] args) {
        int a = 1;
        try {
            int b = a/0;
        } catch (Exception e){
            System.out.println("=======");
        } finally {
            System.out.println(a);
        }
    }

}
