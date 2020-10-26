package com.concurrent.ratelimiter;

import com.concurrent.entity.UserTask;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RateLimiterDemo3 class
 *
 * @author : yuxiang
 * @date : 2020/10/26
 */
public class RateLimiterDemo3 {

    public static void main(String[] args) {
        RateLimiter rateLimiter=RateLimiter.create(2);
        try {
            Thread.sleep(2*1000);
            System.out.println("....");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        ExecutorService threadPool= Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            double acquire=rateLimiter.acquire();
            System.out.println(acquire);
            threadPool.execute(new UserTask(i));
        }
        threadPool.shutdown();
    }
}
