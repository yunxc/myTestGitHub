package com.yun.AESFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 完成对文件的AES加密解密
 *
 */
public class TestAES {

    //初始化向量(IV)，aes 16位
    private static final String IV = "abcdefghijk1mnop";

    public static void main(String[] args){
        try {
            //生成密钥 并保存
            //DigicanAES.getAutoCreateAESKey("d:\\testAES");
            //加密文件
            getAESEncode("d:\\testAES","d://YYYYXXXXYYYYMMDDHHMMSS05.xml","d://encodeXML");
            //解密文件
            getAESDecode("d:\\testAES","d://encodeXML","d://dencodeXML.xml");
            System.out.println("加密解密文件完成了");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成密钥
     * 自动生成AES128位密钥
     * filePath 表示存储密钥的文件路径
     */
    public static void getAutoCreateAESKey(String filePath) throws Exception {
        //实例化一个用AES加密算法的密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        //要生成多少位，只需要修改这里即可128, 192或256。生成128位随机密钥(如要生成256位需要替换jar包(jdk默认支持128位))
        kg.init(128, new SecureRandom());
        //生成一个密钥。
        SecretKey sk = kg.generateKey();
        //返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
        byte[] b = sk.getEncoded();
        //保存该密钥
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(b);
        fos.flush();
        fos.close();
    }

    /**
     * 加密
     * 使用对称密钥进行加密
     * keyFilePath 密钥存放路径
     * sourseFile 要加密的文件
     * encodeFile 加密后的文件
     */
    public static void getAESEncode(String keyFilePath,String sourseFile, String encodeFile) throws Exception{
        //读取保存密钥的文件
        File file = new File(keyFilePath);
        byte[] key = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(key);
        //根据给定的字节数组(密钥数组)构造一个AES密钥。
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        //实例化一个密码器（CBC模式）
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //初始化密码器
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, new IvParameterSpec(IV.getBytes()));
        //读取要加密的文件流
        FileInputStream inputStream = new FileInputStream(sourseFile);
        //输出加密后的文件流
        FileOutputStream outputStream = new FileOutputStream(encodeFile);
        //以加密流写入文件
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
        byte[] b = new byte[1024];
        int len = 0;
        //没有读到文件末尾一直读
        while((len = cipherInputStream.read(b)) != -1) {
            outputStream.write(b, 0, len);
            outputStream.flush();
        }
        cipherInputStream.close();
        inputStream.close();
        outputStream.close();
    }

    /**
     * 解密
     * 使用对称密钥进行解密
     * keyFilePath 密钥存放路径
     * encodeFile 要解密的文件
     * decodeFile 解密后的文件
     */
    public static void getAESDecode(String keyFilePath,String encodeFile, String decodeFile) throws Exception{
        //读取保存密钥的文件
        File file = new File(keyFilePath);
        byte[] key = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(key);
        //根据密钥文件构建一个AES密钥
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        //实例化一个密码器CBC模式的
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //初始化密码器
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, new IvParameterSpec(IV.getBytes()));
        //加密文件流
        FileInputStream inputStream = new FileInputStream(encodeFile);
        //解密文件流
        FileOutputStream outputStream = new FileOutputStream(decodeFile);
        //以解密流写出文件
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = inputStream.read(buffer)) >= 0) {
            cipherOutputStream.write(buffer, 0, r);
        }
        cipherOutputStream.close();
        inputStream.close();
        outputStream.close();
    }

}
