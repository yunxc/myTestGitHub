package com.yun.RSA;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密示例
 */
public class PRSAcoder {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";

    //公钥
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCs9JrxXX04W9oyK9JaU1QWneury6Xl7l/GbakyIzym4i/AQXvprP/RPOLSwJe6WyxGv71cLGgr/BCyW4p8ON5U0D8n+AV+qx2t8NAQuTxaxSfWt+6kTpvqHxn5lVIuO5MYv4L13iDpj0S7zq97O0ukhZsg0h/ddg/hhTTaDWb+eQIDAQAB";

    public static void main(String[] args) throws Exception {
        //需要加密的字符串
        String str = "zyh=123456789&operator_his=1212";
        byte[] pub_str_byte = encryptByPublicKey(str.getBytes(), Base64.decodeBase64(publicKey));
        //将加密后的字节数组通过BASE64转码
        String pub_str_base64 = Base64.encodeBase64String(pub_str_byte);
        System.out.println("明文：" + str);
        System.out.println("密文：" + pub_str_base64);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

}
