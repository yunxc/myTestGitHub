package com.yun.util;

import java.security.MessageDigest;

/**
 * @author z
 * @date 2019/3/1 11:16
 */
public class MD5Util {

    /***
     * 生成MD5
     * @param input byte[]
     * @return String
     * @throws Exception Exception
     */
    public static String getMD5(byte[] input) throws Exception {
        if(input == null){
            return null;
        }
        //拿到一个md5转换器
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input);
        byte[] s = md.digest();
        StringBuilder result = new StringBuilder();
        for (byte value : s) {
            result.append(Integer.toHexString((0x000000FF & value) | 0xFFFFFF00).substring(6));
        }
        return result.toString();
    }

    /**
     * 生成MD5
     * @param input String
     * @return String
     * @throws Exception Exception
     */
    public static String getMD5(String input) throws Exception {
        if(input == null){
            return null;
        }
        byte[] utf8s = input.getBytes("UTF8");
        return getMD5(utf8s);
    }

    public static void main(String[] args) {
        String str = null;
        String input  = "zxcv1230";
        try {
            str = getMD5(input.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

}
