package com.example.smd.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/**
 * @author shanmingda
 */
public class KillDemo {
    /**
     * 启动10个线程
     * 库存6个
     * 生成一个合并队列
     * 每个用户能拿到自己的请求响应
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        KillDemo killDemo = new KillDemo();
        killDemo.mergeJob();
        Thread.sleep(2000);
        List<Future<Result>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            Future<Result> future = executorService.submit(() -> {
                return killDemo.operate(new UserRequest(orderId, userId, 1));
            });
            futureList.add(future);
        }
        futureList.forEach(new Consumer<Future<Result>>() {
            @Override
            public void accept(Future<Result> resultFuture) {
                try {
                    Result result = resultFuture.get(500, TimeUnit.MICROSECONDS);
                    System.out.println(Thread.currentThread().getName() + "客户端响应请求" + result);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
    private Integer stock = 6;

    private BlockingDeque<RequestPromise> queue = new LinkedBlockingDeque<>(10);


    /**
     * 用户进行库存扣减
     */
    public Result operate(UserRequest userRequest) throws InterruptedException {
        // TODO 阈值判断
        // TODO 队列的创建

        RequestPromise requestPromise = new RequestPromise(userRequest);
        boolean enqueueSuccess = queue.offer(requestPromise, 100, TimeUnit.MICROSECONDS);
        if (!enqueueSuccess) {
            return new Result(false, "系统繁忙");
        }
        synchronized (requestPromise) {
            try {
                requestPromise.wait(200);
                if (requestPromise.getResult() == null) {
                    return new Result(false, "等待超时");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return requestPromise.getResult();
    }

    public void mergeJob() {
        new Thread(() -> {
            List<RequestPromise> list = new ArrayList<>();
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    list.add(queue.poll());
                }
                System.out.println(Thread.currentThread().getName() + "合并扣减库存：" + list);

                int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                // 两种情况
                if (sum <= stock) {
                    stock -=sum;
                    // notify user
                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "OK"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    continue;
                }
                for (RequestPromise requestPromise : list) {
                    Integer count = requestPromise.getUserRequest().getCount();
                    if (count <= stock) {
                        stock -= count;
                        requestPromise.setResult(new Result(true, "OK"));
                    } else {
                        requestPromise.setResult(new Result(false, "库存不足"));
                    }
                    synchronized (requestPromise) {
                        requestPromise.notify();
                    }
                }
                list.clear();
            }
        }, "mergeThread").start();

    }
    class RequestPromise {
        private UserRequest userRequest;
        private Result result;

        public RequestPromise(UserRequest userRequest) {
            this.userRequest = userRequest;
        }

        public RequestPromise(UserRequest userRequest, Result result) {
            this.userRequest = userRequest;
            this.result = result;
        }

        public UserRequest getUserRequest() {
            return userRequest;
        }

        public void setUserRequest(UserRequest userRequest) {
            this.userRequest = userRequest;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "RequestPromise{" +
                    "userRequest=" + userRequest +
                    ", result=" + result +
                    '}';
        }
    }


    class Result {
        private Boolean success;
        private String msg;

        public Result(Boolean success, String msg) {
            this.success = success;
            this.msg = msg;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "success=" + success +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }



    static class UserRequest {
        private Long orderId;
        private Long userId;
        private Integer count;


        public UserRequest(Long orderId, Long userId, Integer count) {
            this.orderId = orderId;
            this.userId = userId;
            this.count = count;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "UserRequest{" +
                    "orderId=" + orderId +
                    ", userId=" + userId +
                    ", count=" + count +
                    '}';
        }
    }



}
