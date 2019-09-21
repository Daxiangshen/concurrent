package com.concurrent.waitDemo;

/**
 * <h3>WaitDemo1  Class</h3>
 * wait是会释放锁的。要配合synchronized和notify或者notifyAll使用
 * @author : YuXiang
 * @date : 2019-09-21 10:47
 **/
public class WaitDemo1 {
    public static void main(String[] args) {
        final Object lock=new Object();
        Thread t1=new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"  get lock,waiting");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" release lock ,run over");
            }
        });

        //t1.interrupt();

        Thread t2=new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"  get lock,t1.interrupt");
                t1.interrupt();
                System.out.println(Thread.currentThread().getName()+" release lock ,run over");
            }
        });

        t1.start();
        try {
            //保证t1先启动
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("final: "+t1.getName()+" and "+t2.getName()+"run over!");
        System.out.println("t1's state: "+t1.getState());
        System.out.println("t2's state: "+t2.getState());
    }
}
