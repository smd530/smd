package com.example.smd.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo_01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("task 执行");
                Thread.sleep(1000);
                return 123;
            }
        });
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
        System.out.println("main 线程执行");
    }
}
