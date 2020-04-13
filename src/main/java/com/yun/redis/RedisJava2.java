package com.yun.redis;

import redis.clients.jedis.Jedis;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @date 2019/2/28 13:11
 */
public class RedisJava2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        jedis.select(2);
        jedis.set("name1", "zhang1");
        jedis.set("name2", "zhang2");
        jedis.flushDB();
        System.out.println("name1："+jedis.get("name1"));
        System.out.println("name2："+jedis.get("name2"));
    }
}
