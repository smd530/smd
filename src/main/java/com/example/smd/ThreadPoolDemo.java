package com.example.smd;

import java.util.concurrent.*;

/**
 * @author shanmingda
 */
public class ThreadPoolDemo {

    public static ExecutorService threadPool =
            new ThreadPoolExecutor(20,
                    100,
                    5L,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(150),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        threadPool.execute(() -> {
            System.out.println("do it");
            Thread.currentThread().setName("线程_01");
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(Thread.currentThread().getName() + ":终于执行到了...");
        threadPool.shutdownNow();
    }
}
