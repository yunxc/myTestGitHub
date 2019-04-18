package com.yun.redis;

import com.yun.util.MD5Util;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @date 2019/2/28 13:11
 */
public class RedisJava {
    public static void main(String[] args) throws Exception {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        System.out.println( "name:" + jedis.get("name"));
        User u1 = new User();
        u1.setId(1);
        User u2 = new User();
        u2.setId(2);
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>(16);
        Map<String,Object> m= new HashMap();
        m.put("name","Success");
        m.put("wo",0);
        m.put("u",u2);
        li.add(m);
        u1.setLi(li);
        //jedis.set(SerializeUtil.serialize(u1),SerializeUtil.serialize(u1));
        //User user1=(User)SerializeUtil.unSerialize(jedis.get(SerializeUtil.serialize(u1)));


        User u3 = new User();
        u3.setId(1);
        User u4 = new User();
        u4.setId(2);
        List<Map<String, Object>> li1 = new ArrayList<Map<String, Object>>(16);
        Map<String,Object> m1= new HashMap();
        m1.put("name","Success");
        m1.put("wo",0);
        m1.put("u",u4);
        li1.add(m1);
        u3.setLi(li1);

        User u5 = new User();
        User u6 = new User();
        m1.toString();
        Boolean bool = SerializeUtil.serialize(u5).hashCode()==SerializeUtil.serialize(u5).hashCode();
        if(bool){
            System.out.println("equals相同");
        }else {
            System.out.println("equals不相同");
        }
        Boolean bool1 = u1.hashCode() == u3.hashCode();
        if(bool1){
            System.out.println("hashCode相同");
        }else {
            System.out.println("hashCode不相同");
        }

        boolean b = ClassCompareUtil.compareObject(u1, u3);
        if(b){
            System.out.println("compareObject相同");
        }else {
            System.out.println("compareObject不相同");
        }

        Map<String, Object> m2 = new HashMap(16);
        m2.put("1","ddddd");
        Map<String, Object> m3 = new HashMap(16);
        m3.put("1","ddddd");
        boolean b1 = MD5Util.getMD5(m2.toString()).equals(MD5Util.getMD5(m3.toString()));
        if(b1){
            System.out.println("getMD5相同");
        }else {
            System.out.println("getMD5不相同");
        }
    }
}
