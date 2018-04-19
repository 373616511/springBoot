package com.asyf.springboot.util;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisUtil {
    public static void main(String[] args) {
        //目标库
        Jedis j2 = new Jedis("192.168.0.7");
        j2.auth("123456");
        j2.select(6);
        //原库
        Jedis jedis = new Jedis("47.104.82.194");
        jedis.auth("csstj9030--");
        jedis.select(6);
        Set<String> keys = jedis.keys("*");
        int i = 1;
        for (String key : keys) {
            String s = jedis.get(key);
            System.err.println(i++ + "----" + key);
            //拷贝到目标库
            j2.set(key, s);
        }
    }
}
