package com.concurrent.joinDemo;

/**
 * <h3>JoinDemo1  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-21 09:47
 **/
public class JoinDemo1 {
    public static void main(String[] args) {
        Thread t1=new Thread(() -> {
            try {
                Thread.sleep(2000*2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  run over!");
        });

        Thread mainThread=Thread.currentThread();

        Thread t2=new Thread(() -> {
            //调用主线程的interrupt方法，开启中断标记，会影响主线程中的join方法抛出异常，但是并不会阻碍t1线程的运行
            mainThread.interrupt();
            //修改为t1.interrupt()，会影响t1线程的sleep方法抛出异常，让t1线程结束
            //t1.interrupt();
            System.out.println(mainThread.getName()+" interrupt!");
            System.out.println(Thread.currentThread().getName()+"  run over!");
        });

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName()+" wait"+t1.getName()+" and "+t2.getName()+" run over!");

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final: "+t1.getName()+" and "+t2.getName()+"run over!");
        System.out.println("t1's state: "+t1.getState());
        System.out.println("t2's state: "+t2.getState());
        System.out.println("main's state: "+mainThread.getState());
    }
}
