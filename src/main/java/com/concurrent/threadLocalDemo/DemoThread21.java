package com.concurrent.threadLocalDemo;

/**
 * DemoThread21 Class
 *
 * @author : yuxiang
 * @date : 2019/11/18
 */
public class DemoThread21 extends Thread{

    //多个线程之间读取副本，父子线程之间复制传递
    public static InheritableThreadLocal<String> t1=new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException{
        t1.set("123");

        Thread t0=new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"get t1 is:"+t1.get());
            t1.set("After Set The Value Change to"+Thread.currentThread().getName());
        });

        Thread t2=new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"get t1 is:"+t1.get());
            t1.set("After Set The Value Change to"+Thread.currentThread().getName());
        });

        t0.start();

        Thread.sleep(1000);

        t2.start();

        System.out.println(Thread.currentThread().getName()+"get t1 is:"+t1.get());
    }
}
