package com.yun.main;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/12/15 17:34
 */
public class Main12 {
    public static void main(String[] args) {
        String t = "order by";
        String case_ = "CAST(";
        List<String> ls = new ArrayList<>();
        String s1 = "order by CAST(A AS DECIMAL) asc,B asc,C desc";
        String[] split2 = s1.split(t);
        String[] split = s1.split(t)[1].split(",");
        for(String s:split){
            if(s.contains(case_)){
                String[] split1 = s.split("CAST\\(");
                //ls.add(case_ + "t2." + split1[1]);
            } else {
                //ls.add("t2." + s);
            }
        }
        System.out.println(t + " " + StringUtils.join(ls,","));
    }
}
