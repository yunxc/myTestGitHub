package com.yun.js;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Administrator
 * @date 2019/9/21 17:40
 */
public class Main {
    public static void main(String[] args) {
        String js = "{\n" +
                "    xAxis: {\n" +
                "        type: 'category',\n" +
                "        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n" +
                "    },\n" +
                "    yAxis: {\n" +
                "        type: 'value'\n" +
                "    },\n" +
                "    series: [{\n" +
                "        data: [820, 932, 901, 934, 1290, 1330, 1320],\n" +
                "        type: 'line'\n" +
                "    }]\n" +
                "}";
        Object parse = JSONObject.parse(js);
        System.out.println(parse.toString());
    }
}
