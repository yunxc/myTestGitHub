package com.yun.main;

import com.yun.util.MD5Util;
import org.apache.commons.codec.binary.Base64;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author Administrator
 * @date 2019/10/28 21:59
 */
public class Main5 {

    public static void main(String[] args) throws Exception {
        String str="34.2%";
        NumberFormat nf=NumberFormat.getPercentInstance();
        Number parse = nf.parse(str);
        System.out.println(parse);
        LocalDateTime nowTime= LocalDateTime.now();
        int nano = nowTime.getDayOfMonth();
        nowTime.getYear();
        int value = nowTime.getMonth().getValue();
        System.out.println(nano);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest("123".getBytes("UTF-8"));
        byte[] bytes1 = Base64.encodeBase64(bytes);
        String s = bytes1.toString();

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(s.getBytes("UTF-8"));

        String md51 = MD5Util.getMD5(digest);

    }

}
