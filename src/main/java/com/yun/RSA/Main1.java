package com.yun.RSA;

import com.yun.util.HttpClientUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import net.iharder.Base64;

/**
 * @author Administrator
 * @date 2019/7/25 14:06
 */
public class Main1 {

        public static final String CHARSETNAME = "UTF-8";
        public static final String ALGORITHM = "RSA";

        /**
         * RSA最大加密明文大小
         */
        private static final int MAX_ENCRYPT_BLOCK = 53;

        private static final String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK+2Df2Y6GszsQk1nPieHcYej6+0GDE+mUzuJkrzYm5wJRCa8xmvchDZRaDCOX++mUoiqVcD300D5HYpCrlwzqcCAwEAAQ==" ;

        public static void main(String[] args) throws Exception {
            run();
        }

        public static void run(String... args) throws Exception {
		/*long time = System.currentTimeMillis();
		String param = "{\"organizationId\":\""+3001
				+"\",\"doctorName\":\""+"姜航"
				+"\",\"patientId\":\""+"12346"
				+"\",\"doctorCode\":\""+"1010"
				+"\",\"timestamp\":"+time+"}";
		String ticket = encryptByPub(param.getBytes(), pubKey);*/

            for (int i = 0; i < 1; i++) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        String param = "{\"organizationId\":\""+3001
                                +"\",\"doctorName\":\""+"姜航"
                                +"\",\"patientId\":\""+"12346"
                                +"\",\"doctorCode\":\""+"1010"
                                +"\",\"timestamp\":"+time+"}";
                        try {
                            String ticket = encryptByPub(param.getBytes(), pubKey);
                            HttpClientUtil.sendGet("http://172.16.9.60:9967/aiap/patient/query?ticket="+encodeURIComponent(ticket));
                            System.err.println("thread success");
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        }

        public static String encodeURIComponent(String s) {
            String result = null;
            try {
                result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
                        .replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
                        .replaceAll("\\%7E", "~");
            } catch (UnsupportedEncodingException e) {
                result = s;
            }
            return result;
        }

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

        public static String base64Encoded(byte[] param) {
            //return Base64.getEncoder().encodeToString(param);
            return Base64.encodeBytes(param);
        }


        public static byte[] base64Decoded(String param) {
            //return Base64.getDecoder().decode(param);
            try {
                return Base64.decode(param);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

