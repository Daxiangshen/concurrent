package com.concurrent.ratelimiter;

import com.concurrent.entity.UserTask;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiterDemo2 class
 *
 * 好处是可以根据boolean值决定后续逻辑
 *
 * @author : yuxiang
 * @date : 2020/10/26
 */
public class RateLimiterDemo2 {

    public static void main(String[] args) {
        RateLimiter rateLimiter=RateLimiter.create(10);
        ExecutorService threadPool= Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            //boolean tryAcquire=rateLimiter.tryAcquire();  //并发,没有等待,所以只有一个为true
            //boolean tryAcquire=rateLimiter.tryAcquire(1, TimeUnit.SECONDS); //等待一秒取令牌
            boolean tryAcquire=rateLimiter.tryAcquire(2,1, TimeUnit.SECONDS); //等待一秒取令牌,并且一次取出两个令牌
            System.out.println("是否获得许可:"+tryAcquire);
            if(tryAcquire){
                threadPool.execute(new UserTask(i));
            }
        }
        threadPool.shutdown();
    }
}
