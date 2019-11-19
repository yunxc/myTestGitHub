package com.yun.main;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import scala.util.parsing.json.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author yunlong.zhang
 * @date 2019/4/27 15:15
 */
public class Main2 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        String a = null;
        System.out.println(sb.length());
        //code1=1&code2=acf
        //= --> "="     & --> ","
        String parameter = "code1 = 1& code2 = acf";
        parameter = parameter.replaceAll("\\s*", "")
                .replaceAll("=","\":\"")
                .replaceAll("\\&", "\",\"");
        parameter = "{\"" + parameter + "\"}";
        HashMap<String, Object> parameterMap = JSONObject.parseObject(parameter, HashMap.class);
        StringJoiner sj = new StringJoiner(",");
        System.out.println(sj.toString());
        for(int i = 1; i < 5; i++){
            sj.add(i + "");
        }
        System.out.println(sj);

        String q = "AC";
        System.out.println("AB".equalsIgnoreCase(q));
        String nn = "  a";
        String[] s = nn.split(" ");
        System.out.println("****************");
        for(String t:s){
            System.out.println(t);
            System.out.println(t.length());
        }
        System.out.println("****************");

        Map<String, Object> map = new HashMap<>();

    }
}
