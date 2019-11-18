package com.concurrent.actomicDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DemoThread17 Class
 * Atomic类不能保证成员方法的原子
 * @author : yuxiang
 * @date : 2019/11/13
 */
public class DemoThread17 implements Runnable{

    //原子类
    private static AtomicInteger sum=new AtomicInteger(0);

    //如果add方法是原子性的，那么每次的结果都是10的整数倍

    public static void add(){
        sum.addAndGet(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sum.addAndGet(9);
        System.out.println(sum);
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {
        ExecutorService es= Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            es.submit(new DemoThread17());
        }
        es.shutdown();
    }
}
