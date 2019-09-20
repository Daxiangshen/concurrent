package com.concurrent.interruptDemo;

/**
 * <h3>InterruptDemo1  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-20 14:27
 **/
public class InterruptDemo1 {
    public static void main(String[] args) {
        Thread t1=new Thread(() -> {
            //interrupted方法获取的是当前线程的中断状态。并且会改变当前线程的中断状态
            while (!Thread.interrupted()){

            }
            System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().isInterrupted());
        });

        t1.start();

        //中断标记设置为ture
        t1.interrupt();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main is run over!");
    }
}
