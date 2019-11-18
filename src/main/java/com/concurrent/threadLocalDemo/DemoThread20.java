package com.concurrent.threadLocalDemo;

/**
 * DemoThread20 Class
 *
 * @author : yuxiang
 * @date : 2019/11/18
 */
public class DemoThread20 {

    public static ThreadLocal<String> t1=new ThreadLocal<String>();

    public static void main(String[] args) {
        t1.set("123");

        Thread t0=new Thread(() -> {
            //子线程无法获取父线程（main线程）的值 ，他们是两个独立的用户线程
            System.out.println(Thread.currentThread().getName()+"get t1 is:"+t1.get());
        });

        t0.start();

        System.out.println(Thread.currentThread().getName()+"get t1 is+"+t1.get());
    }
}
