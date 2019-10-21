package com.concurrent.synchronizedDemo;

/**
 * DemoThread03 Class
 * 同一个对象内的多个synchronized方法可以锁重入
 * @author : yuxiang
 * @date : 2019/10/21
 */
public class DemoThread03 {

    public synchronized void run1(){
        System.out.println(Thread.currentThread().getName()+">run1...");
        //调用同类中的synchronized方法不会引起死锁
        run2();
    }

    public synchronized void run2(){
        System.out.println(Thread.currentThread().getName()+">run2...");
    }

    public static void main(String[] args) {
        final DemoThread03 demoThread03=new DemoThread03();
        Thread thread=new Thread(demoThread03::run1);
        thread.start();
    }
}
