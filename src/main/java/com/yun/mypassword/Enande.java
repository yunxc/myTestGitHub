package com.yun.mypassword;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Administrator
 * @date 2019/11/13 16:39
 */
public class Enande {

    /**
     * @param sourceString
     * @param password
     * @return 密文
     */
    private static String encrypt(String sourceString, String password) {
        char[] p = password.toCharArray();
        int n = p.length;
        char[] c = sourceString.toCharArray();
        int m = c.length;
        for (int k = 0; k < m; k++) {
            int mima = c[k] + p[k / n];
            c[k] = (char) mima;
        }
        return new String(c);
    }

    /**
     * 自定义加密方法
     * @param sourceString 明文
     * @return
     */
    private static String myEncrypt(String sourceString){
        BASE64Encoder encoder = new BASE64Encoder();
        String encrypt = encrypt(sourceString, "@;huoshu_152R*#@=d=BHdninal;md;][0mddfa7892h84jj84%8393n03n03nian,zz];[.a(%32893dJHf");
        try {
            String base1 = encoder.encode(encrypt.getBytes("UTF-8"));
            return encoder.encode(base1.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * 使用例子
     */
    public static void main(String arg[]) throws Exception {
        String wen = "<,K./0i15k<=>?@ABCD;8H\u0089,K./0i1534j6|\u0082p}\u007F\u0084\u0080tz{fhg2Fwvusrqo~l\u0085\u0083n\u0081myx79:\\bP]_d`TZ[\u0086\u0088\u0087-ERFECBA?N<US>Q=IH79:佛奸杨巙";
        String pass = "@;huoshu_152R*#@=d=BHdninal;md;][0mddfa7892h84jj84%8393n03n03nian,zz];[.a(%32893dJHf";
        //System.out.println(encrypt(wen, pass));
        //System.out.println(decrypt(encrypt(wen, pass), pass));

        String encrypt = encrypt(wen, pass);
        BASE64Encoder encoder = new BASE64Encoder();
        String encode1 = encoder.encode(encrypt.getBytes("UTF-8"));
        System.out.println("密文：" + encrypt);
        System.out.println("密文BASE64_1：" + encode1);
        String encode2 = encoder.encode(encode1.getBytes("UTF-8"));
        System.out.println("密文BASE64_2：" + encode2);


        encode2 = "WWNLQVkyUmx3cDVtYXNLZ2NYSnpkSFYyZDNoNWNHMTl3cjVod29CalpHWENubVpxYUduQ24ydkNzY0szd3FYQ3NzSzB3cm5DdGNLcHdxL0NzTUtid3AzQ25HZDd3cXpDcThLcXdxakNwOEttd3FUQ3M4S2h3cnJDdU1LandyYkNvc0t1d3Exc2JtL0NrY0tYd29YQ2tzS1V3cG5DbGNLSndvL0NrTUs3d3IzQ3ZHSjZ3b3pDaHNLRndvUENnc0tCZjhLT2ZNS1Z3cE4rd3BGOXdvbkNpSGQ1ZXVTK20rV211T2FlcU9XNG1YQndjQT09";

        encode2 = myEncrypt(wen);

        BASE64Decoder decoder = new BASE64Decoder();
        String s = new String(decoder.decodeBuffer(encode2), "UTF-8");
        String s1 = new String(decoder.decodeBuffer(s), "UTF-8");
        String decrypt = myDecoder(encode2);
        System.out.println("明文：" + decrypt);



    }

    /**
     * 自定义解密方式
     * @param pass
     * @return
     */
    private static String myDecoder(String pass){
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64.Decoder decoder = Base64.getDecoder();
        try {
            String base1 = new String(decoder.decodeBuffer(pass), "UTF-8");
            String base2 = new String(decoder.decodeBuffer(base1), "UTF-8");
            return decrypt(base2, "@;huoshu_152R*#@=d=BHdninal;md;][0mddfa7892h84jj84%8393n03n03nian,zz];[.a(%32893dJHf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     *
     * @param sourceString 密文
     * @param passwordKey 秘钥
     * @return 明文
     */
    private static String decrypt(String sourceString, String passwordKey) {
        //字符串转字符数组
        char[] p = passwordKey.toCharArray();
        //密码长度
        int n = p.length;
        char[] c = sourceString.toCharArray();
        //字符串长度
        int m = c.length;
        for (int k = 0; k < m; k++) {
            //解密
            int mima = c[k] - p[k / n];
            c[k] = (char) mima;
        }
        return new String(c);
    }


    }
