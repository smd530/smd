package com.example.smd.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author shanmingda
 */
public class CompletableFutureDemo_01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("123" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 123;
        }, executorService).thenApplyAsync(integer -> {
            System.out.println("thenApply" + Thread.currentThread().getName());
            System.out.println("thenApply..." + integer);
            return integer;
        }, executorService);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("456" + Thread.currentThread().getName());
            return 456;
        }, executorService);
        CompletableFuture<Object> objectCompletableFuture = future.thenCombine(future1, (BiFunction<Integer, Integer, Object>) (integer, integer2) -> {
            System.out.println("sum" + Thread.currentThread().getName());
            return integer + integer2;
        }).thenApplyAsync(o -> {
            System.out.println("last" + Thread.currentThread().getName());
            return "the last";
        }, executorService);

        System.out.println(objectCompletableFuture.get());

        executorService.shutdown();

    }

}
