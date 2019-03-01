package com.yun.redis;

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
    public static void main(String[] args) throws NoSuchAlgorithmException {
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
        m.put("u2",u2);
        li.add(m);
        u1.setLi(li);
        //jedis.set(SerializeUtil.serialize(u1),SerializeUtil.serialize(u1));
        User user1=(User)SerializeUtil.unSerialize(jedis.get(SerializeUtil.serialize(u1)));
    }
}
