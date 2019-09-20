package com.concurrent.sleepDemo;

/**
 * <h3>SleepDemo0  Class</h3>
 * 不释放锁，睡眠结束后进入就绪状态
 * @author : YuXiang
 * @date : 2019-09-19 16:29
 **/
public class SleepDemo0 {
    public static void main(String[] args) {
        final Object lock=new Object();

        Thread t1=new Thread(() -> {
            synchronized (lock){
                System.err.println(Thread.currentThread().getName()+"  get lock,sleeping");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName()+"  sleep over and run over!");
            }
        });

        Thread t2=new Thread(() -> {
            synchronized (lock){
                System.err.println(Thread.currentThread().getName()+"  get lock,sleeping");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName()+"  sleep over and run over!");
            }
        });

        t1.start();
        t2.start();

        //打断睡眠
        t1.interrupt();
    }
}
