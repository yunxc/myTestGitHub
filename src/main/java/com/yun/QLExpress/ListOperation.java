package com.yun.QLExpress;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/1/4 11:01
 */
public class ListOperation {

    public boolean contain(List<String> list, String content){
        if(list.contains(content)){
            return true;
        }
        return false;
    }

}
