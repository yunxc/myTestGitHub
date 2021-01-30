package com.yun.jdk8.function;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @date 2020/11/27 19:39
 */
public class Main1 {
    public static void main(String[] args) {
        Optional<String> userOptional = Optional.ofNullable("user");
        LocalTime now = LocalTime.now();
        LocalDateTime now1 = LocalDateTime.now();
        List li = new ArrayList();
        li.add("1");
        li.add("2");
        li.add("3");
        String join = String.join(",",li);
        System.out.println(join);

    }
}
