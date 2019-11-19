package com.yun.main;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/10/15 15:04
 */
public class Main3 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        List<List<String>> sprateList = ListUtils.partition(list,2);
        //System.out.println(sprateList);
        System.out.println(6 / 7);
    }
}
