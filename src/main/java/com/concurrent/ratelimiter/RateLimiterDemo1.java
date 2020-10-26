package com.concurrent.ratelimiter;

import com.concurrent.entity.UserTask;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RateLimiterDemo1 class
 *
 * @author : yuxiang
 * @date : 2020/10/26
 */
public class RateLimiterDemo1 {

    public static void main(String[] args) {
        /**
         * 使用指定的稳定吞吐量参数创建一个RateLimiter,参数为”每秒许可“(通常称为QPS,每秒查询)
         * 2代表每秒允许释放2个许可证或每秒允许两次请求
         * 返回的RateLimiter确保在任何给定的秒内平均不超过permitsPerSecond,持续的请求在每秒钟平稳的传播
         * 当传入请求率超过permitsPerSecond,速率限制器将每(1.0/permitsPerSecond秒)秒释放一个许可证
         * 当速率限制器未被使用时,将允许对permitsPerSecond许可的爆发
         * 随后的请求以稳定的permitsPerSecond的稳定速率被限制
         * */
        RateLimiter rateLimiter=RateLimiter.create(10);  //参数为每秒放几个令牌
        ExecutorService threadPool= Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            //从RateLimiter符获得一个许可,阻塞直到请求被授予.如果有的话,返回睡眠时间.这个方法等价于acquire(1)
            double waitSecond=rateLimiter.acquire();  //获取令牌(参数为获取令牌的个数),返回值为获取令牌的消耗的时间
//            waitSecond=rateLimiter.acquire(2);  //必须获得指定的许可数量才能执行
            System.out.println("等待时间:"+waitSecond);
            threadPool.execute(new UserTask(i));
        }
        threadPool.shutdown();
    }
}
