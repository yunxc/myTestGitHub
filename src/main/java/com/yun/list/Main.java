package com.yun.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * @author yunlong.zhang
 * @date 2019/6/19 10:23
 */
public class Main {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        for(int i = 0; i < 1000; i++){
            arrayList.add(i + "");
        }

        Iterator<String> it = arrayList.iterator();

        //Iterator<String> it2 = arrayList.iterator();
        int a = 0;
        while(it.hasNext()){
            String s1 = it.next();
            while(it.hasNext()){
                System.out.println(a++);
                String s2 = it.next();
                //ddddddd
                it.remove();


                if(s1!=s2 && "200".equals(s1) && "300".equals(s2))
                {
                    System.out.println(s1 + "!===============" + s2);
                    it.remove();
                }


            }

        }
    }

}
