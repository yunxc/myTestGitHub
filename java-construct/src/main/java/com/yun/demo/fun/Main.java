package com.yun.demo.fun;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Administrator
 * @date 2021/6/2 19:28
 */
public class Main {

    public static void main(String[] args) {
        funSupplier(()->{
            return "111111";
        });
    }

    public static void funSupplier(Supplier<String> supplier){
        String s = supplier.get();
        System.out.println(s);
    }

    public static void funConsumer(Consumer<String> c, String s){
        c.accept(s);
    }

}
