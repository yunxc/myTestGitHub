package com.yun.base64;

import org.apache.commons.codec.binary.Base64;

import java.util.regex.Pattern;

/**
 * @author Administrator
 * @date 2019/11/19 15:26
 */
public class Main {
    public static void main(String[] args) {
        String base64Pattern = "/^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}(=)?|[A-Za-z0-9+/]{2}(==)?|[A-Za-z0-9+/]{1}(===)?)$/";
        String str = "Y2Zhc2ZzYWZhc2Y=s";
        try {
            byte[] bytes = Base64.decodeBase64(str);
            System.out.println(bytes);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(Pattern.matches(base64Pattern, str));
    }
}
