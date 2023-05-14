package com.example.smd.redisdemo;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author shanmingda
 */
public class JedisDemo {

    public static void main(String[] args) {
        // 1 connection通过指定ip端口号获取连接
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2 指定密码
        // 3 获得jedis客户端 访问redis
//        // keys
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }
        System.out.println(jedis.get("k2"));
    }
}
