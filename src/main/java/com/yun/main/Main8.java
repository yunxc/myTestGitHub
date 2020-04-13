package com.yun.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.json.GsonUtil;
import org.apache.commons.beanutils.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/3/17 16:03
 */
public class Main8 {
    public static void main(String[] args) {
        String s = "loginName=dmin&org=nij";
        String ss = s.replaceAll("&","\",\"").replaceAll("=","\":\"");
        ss = "{\"" + ss + "\"}";
        JSONObject jsonObject = JSON.parseObject(ss);
        System.out.println(jsonObject);
        Map parse = (Map)JSON.parse(ss);
        System.out.println(parse);
    }

}
