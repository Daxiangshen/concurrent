package com.concurrent.synchronizedDemo;

/**
 * <h3>DemoThread01  Class</h3>
 * 对象锁只针对synchronized修饰的方法生效，对象中所有的synchronized方法都会同步执行，非synchronized方法异步执行
 * @author : YuXiang
 * @date : 2019-09-23 13:53
 **/
public class DemoThread01 {

    public synchronized void print1(){
        System.out.println(Thread.currentThread().getName()+">hello!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void print2(){
        System.out.println(Thread.currentThread().getName()+">hello!");
    }

    public static void main(String[] args) {
        final DemoThread01 thread=new DemoThread01();
        Thread t1=new Thread(() -> {
            thread.print1();
        },"thread1");

        Thread t2=new Thread(() -> {
            thread.print2();
        },"thread2");

        t1.start();
        t2.start();
    }
}
