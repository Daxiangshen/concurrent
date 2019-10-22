package com.concurrent.deadlockDemo;

import com.concurrent.synchronizedDemo.DemoThread07;

/**
 * DemoThread Class
 * 死锁  至少两个线程才会发生。自己不会，因为锁重入
 * @author : yuxiang
 * @date : 2019/10/22
 */
public class DemoThread09 {
    private Object lock1=new Object();
    private Object lock2=new Object();

    public void execute1(){
        synchronized (lock1){
            System.out.println("线程"+Thread.currentThread().getName()+"获得lock1执行execute1开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("线程"+Thread.currentThread().getName()+"获得lock2执行execute1开始");
            }
        }
    }

    public void execute2(){
        synchronized (lock2){
            System.out.println("线程"+Thread.currentThread().getName()+"获得lock2执行execute2开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1){
                System.out.println("线程"+Thread.currentThread().getName()+"获得lock1执行execute2开始");
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread09 demo=new DemoThread09();
        new Thread(demo::execute1,"t1").start();
        new Thread(demo::execute2,"t2").start();
    }
}
