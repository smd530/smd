package com.example.smd;

import java.util.concurrent.*;

public class ThreadPoolDemo_02 {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(5,
            10,
            5L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(20),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<String> th1 = THREAD_POOL.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            return Thread.currentThread().getName();
        });

        Future<String> th2 = THREAD_POOL.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            return Thread.currentThread().getName();
        });

        System.out.println(th1.get());
        System.out.println(th2.get());


        THREAD_POOL.shutdown();

        System.out.println("lalalalalal");

    }



}
