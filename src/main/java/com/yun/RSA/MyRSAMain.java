package com.yun.RSA;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Administrator
 * @date 2019/11/19 13:38
 */
public class MyRSAMain {

    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";

    //公钥
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCs9JrxXX04W9oyK9JaU1QWneury6Xl7l/GbakyIzym4i/AQXvprP/RPOLSwJe6WyxGv71cLGgr/BCyW4p8ON5U0D8n+AV+qx2t8NAQuTxaxSfWt+6kTpvqHxn5lVIuO5MYv4L13iDpj0S7zq97O0ukhZsg0h/ddg/hhTTaDWb+eQIDAQAB";

    //私钥
    private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKz0mvFdfThb2jIr0lpTVBad66vLpeXuX8ZtqTIjPKbiL8BBe+ms/9E84tLAl7pbLEa/vVwsaCv8ELJbinw43lTQPyf4BX6rHa3w0BC5PFrFJ9a37qROm+ofGfmVUi47kxi/gvXeIOmPRLvOr3s7S6SFmyDSH912D+GFNNoNZv55AgMBAAECgYBJ1zS/j7aCdVS2ztbEEYd5EHspoYx2mkrxoKf36+tdJHxWjJ6mqWGep2v/ppIg0gQZQItL6vmercNDIqoBVxY0UhX+jwQ5oZzEA6Fu3VT9v+9McHN3yj06/6maAK7nhOSNe231AyRpWBNnxSwYLqCEIaCB6SesVn5KKgakTSjRsQJBAPMpuhZeAi1gfehInLqh4zHZ20pWQBEDWB3wlpRavA0KmpeNwBaSDpRR4357v5wxv/94O5x6+RZrJJQBerJ7MasCQQC2Fguk8zNjlh+GMSIZuTeq5oRzon2Dj6pxjBybEhZS37g8kUIQ9bCrRNMvMTtb1BNsJWzAcbcLxkFiysq797RrAkEAle0sZ44fGYtdFlNvD0OxqZMBupvjdJnzQsKMlXw8Zm4bU0Z7IW98vcaf1eTHB69FCmc/mgPYgRIew4MF+/YpcQJBAKjMySCygLpaioDpfZZhOTUKnFFimn7jKXw4CrDLVLyhP3eOpbN5QL2VgT2no0Ke2R6Rlz7UbH/S2/zMyKL2oF8CQEYwjmGcrjPel15SCD0KUVvTgcvYVcH9H8EqUY59or/vvdfhl7yiXllHhzybi1cj0fH3bJjZzIOY3VHt1pOfju0=";

    public static void main(String[] args) throws Exception {
        String str = "zyh=125-ddmo";
        byte[] pub_str_byte = encryptByPublicKey(str.getBytes(), Base64.decodeBase64(publicKey));
        System.out.println(pub_str_byte.length);
        String pub_str_base64 = Base64.encodeBase64String(pub_str_byte);
        System.out.println(pub_str_base64.length());
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
        System.out.println("原文：" + str);
        System.out.println("加密后Base64的数据：" + pub_str_base64);

        //甲方使用私钥对数据进行解密
        //pub_str_base64 = "G9lBPCUBGscDw28V5gdqVSXCOj3BsWbds8j0O7qMcNmWKbIK8BGRTs0AbqgvCKKjlQcDhNsI0Gvak3UBsgcKtg==";
        //pub_str_base64 = "123";
        byte[] bytes = Base64.decodeBase64(pub_str_base64);
        System.out.println(bytes.length);
        byte[] decode2 = decryptByPrivateKey(bytes, Base64.decodeBase64(privateKey));
        System.out.println("解密后的数据：" + new String(decode2));

    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

}
