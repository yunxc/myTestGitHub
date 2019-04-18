package com.yun.RSA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yunlong.zhang
 * @date 2019/3/27 9:49
 */
public class Jm {

    // 私钥
    private static final String PRIKEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAr7YN/ZjoazOxCTWc+J4dxh6Pr7QYMT6ZTO4mSvNibnAlEJrzGa9yENlFoMI5f76ZSiKpVwPfTQPkdikKuXDOpwIDAQABAkEAmpj1ylEE/X1noJC5Yc3MqIovJgqw1nMbcqJ+sqddzYgM7Nv2r+qfA6fAbPUohCfsDU+++RApenEHfu5l2zYWYQIhANgTCE1WfTD4h8W4aCjuM2JjJMD98O2q7rZoET+CbheZAiEA0C24tNKGbRvN9ftYt6dpYh8VXZQCjNviipY0ZkbBAD8CIH8/Ph1leNaVFkgvYE/yb5pabrOlGPSuPGR5hubS5gjJAiAP8JBF+CCm1gwtG3OtjDn7c1nVcnHHi0aLSfj1I9G/TQIgYKefqXPsdBYZId1mcsUSAnoaRn858//L/+hEUtsrGVU=";

    public static final String ALGORITHM = "RSA";

    public static final String salt = "firesoon@qwezxloj8973d!";


    /**
     * Ascii码
     * @param psnid
     * @return
     */
    private static String Md5(String psnid) {
        return DigestUtils.md5Hex(psnid + salt);
    }

    /**
     * 获取超级密码
     * @return
     */
    private static String getPassword() {
        Calendar calendar = Calendar.getInstance();
        String password = String.format("%shuo_sh%su-@ad%smin!-_$#*%%&&sikida#@!",calendar.get(Calendar.MONTH) , calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE));
        return password;
    }

    private String rex(String line, String pattern) {

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new RuntimeException("获取登录页面参数失败");
        }
    }




    public static void main(String[] args) throws Exception {
        String mw = "hHDxkq4cNyRl9hbUM0tW6UZiKDCeh29yxQEa5QB9HSHTRDDH/ko1tSbeNX9T/eJNipiyPwNKY0dIAQMxlScZWJwKqpeys82tMx/060cPFoYFmUgH0vA0/HxmeZud4uxdSFA6RMM1ABOvIjj7mgUlea4pqWPgKXGXOaX5UrGfXEA=";
        String a = decryptByPri(mw);
        System.out.println(a);
    }

    /**
     * 	公钥解密
     * @param encryptData
     * @return
     * @throws Exception
     */
    public static String decryptByPri(String encryptData) throws Exception {
        // 私钥有经过base64编码，先解码
        byte[] priByte = Base64.getDecoder().decode(PRIKEY);
        // 还原私钥
        PKCS8EncodedKeySpec pks = new PKCS8EncodedKeySpec(priByte);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        Key priKey = factory.generatePrivate(pks);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        // 加密的数据有经过base64编码，所以先解码
        byte[] encryptByte = Base64.getDecoder().decode(encryptData);

        int inputLen = encryptByte.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for (int i = 0; inputLen - offSet > 0; offSet = i * 64) {
            byte[] cache;
            if (inputLen - offSet > 64) {
                cache = cipher.doFinal(encryptByte, offSet, 64);
            } else {
                cache = cipher.doFinal(encryptByte, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        String decryptedData = out.toString("UTF-8");
        out.close();
        return decryptedData;
    }

}
