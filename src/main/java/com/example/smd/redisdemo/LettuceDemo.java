package com.example.smd.redisdemo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author shanmingda
 */
public class LettuceDemo {
    public static void main(String[] args) {
        // 1 使用builder链式编程 RedisURI
        RedisURI uri = RedisURI.Builder
                .redis("127.0.0.1")
                .withPort(6379).build();
        // 2 创建连接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        // 3 创建操作的command
        RedisCommands<String, String> commands = connect.sync();
        // 业务逻辑
        commands.set("k10", "12345");
        System.out.println("<<<<<<<<<<<<<<<<<< " + commands.get("k10"));
        // 业务逻辑

        // 4 各种关闭释放资源
        connect.close();
        redisClient.shutdown();
    }
}
