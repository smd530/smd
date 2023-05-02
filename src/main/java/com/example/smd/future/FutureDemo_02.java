package com.example.smd.future;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class FutureDemo_02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        List<Future<Integer>> futureList = Lists.newArrayList();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future01 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("1执行");
                Thread.sleep(3000);
                return 1;
            }
        });
        Future<Integer> future02 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("2执行");
                return 2;
            }
        });
        Future<Integer> future03 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("3执行");
                Thread.sleep(1000);
                return 3;
            }
        });

        futureList.add(future01);
        futureList.add(future02);
        futureList.add(future03);

        for (Future<Integer> future : futureList) {
            Integer integer = future.get();
            System.out.println(integer);
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

    }
}
