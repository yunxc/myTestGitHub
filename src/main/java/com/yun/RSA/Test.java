package com.yun.RSA;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author yunlong.zhang
 * @date 2019/3/27 9:34
 */
public class Test {

    public static final String CHARSETNAME = "UTF-8";
    public static final String ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 53;

    private static final String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK+2Df2Y6GszsQk1nPieHcYej6+0GDE+mUzuJkrzYm5wJRCa8xmvchDZRaDCOX++mUoiqVcD300D5HYpCrlwzqcCAwEAAQ==" ;

    public static void main(String[] args) {
        String data = "abm";
        try {
            String pass = encryptByPub(data.getBytes(), pubKey);
            System.out.println("明文:" + data);
            System.out.println("密文:" + pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(Math.floorDiv(4, 3));
    }

    /**
     * 通过公钥加密
     *
     * @param data
     * @param pubKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String encryptByPub(byte[] data, String pubKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException {
        // pubKey是经过base64编码的，先解码
        byte[] pubByte = base64Decoded(pubKey);
        // 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
        X509EncodedKeySpec xeks = new X509EncodedKeySpec(pubByte);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        Key publicKey = factory.generatePublic(xeks);

        // 加密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptByte = out.toByteArray();
        out.close();
        // 返回加密后由Base64编码的加密信息
        String encryptStr = base64Encoded(encryptByte);
        return encryptStr;
    }

    /**
     * base64编码
     *
     * @param param
     * @return
     */
    public static String base64Encoded(byte[] param) {
        return Base64.getEncoder().encodeToString(param);
    }

    /**
     * base64解码
     *
     * @param param
     * @return
     */
    public static byte[] base64Decoded(String param) {
        return Base64.getDecoder().decode(param);
    }

}
