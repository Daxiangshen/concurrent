package com.concurrent.casDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * DemoThread18 Class
 * AtomicStampedReference 解决cas的ABA问题
 * @author : yuxiang
 * @date : 2019/11/14
 */
public class DemoThread18 {
    //两个参数： 1.初始值  2.初始的时间戳，也就是版本号
    private static AtomicStampedReference<Integer> atomic=new AtomicStampedReference<>(100,0);

    public static void main(String[] args) throws InterruptedException {
        Thread t0=new Thread(() -> {
            try {
                //睡眠一秒
                TimeUnit.SECONDS.sleep(1);
                //compareAndSet 对比并且设置一个值   参数：期望值，改变后的值，期望的时间戳，改变后的时间戳
                boolean success=atomic.compareAndSet(100,101,atomic.getStamp(),atomic.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"set 100>101 :"+success);
                success=atomic.compareAndSet(101,100,atomic.getStamp(),atomic.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"set 101>100 :"+success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t0.start();

        Thread t1=new Thread(() -> {
            try {
                int stamp=atomic.getStamp();
                System.out.println(Thread.currentThread().getName()+"修改之前"+stamp);
                TimeUnit.SECONDS.sleep(2);
                int stamp1=atomic.getStamp();
                System.out.println(Thread.currentThread().getName()+"等待两秒后，版本被线程t0修改为:"+stamp1);
                //以下两次修改不会成功，因为版本不符，虽然期待值是相同的，因此解决了ABA问题
                boolean success=atomic.compareAndSet(100,101,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName()+"set 100>101 使用错误的时间戳:"+success);
                success=atomic.compareAndSet(101,100,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName()+"set 101>100 使用错误的时间戳:"+success);

                //以下修改是成功的，因为使用了正确的版本号，正确的期待值
                success=atomic.compareAndSet(100,101,stamp1,stamp1+1);
                System.out.println(Thread.currentThread().getName()+"set 100>101 使用正确的时间戳:"+success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        t0.join();
        t1.join();

        System.out.println("main is over");
    }
}
