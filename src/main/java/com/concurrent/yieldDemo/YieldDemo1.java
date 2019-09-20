package com.concurrent.yieldDemo;

/**
 * <h3>YieldDemo1  Class</h3>
 * 不释放锁,让出时间片。虚拟的。还可以被选择
 * @author : YuXiang
 * @date : 2019-09-20 10:14
 **/
public class YieldDemo1 implements Runnable{

    public static Object lock=new Object();

    @Override
    public void run() {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+"  yield");

            //Thread.yield();

            //wait方法是释放锁的.与synchronized结合并且与notify或者notifyAll方法结合使用
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"  run over!");
        }
    }

    public static void main(String[] args) {
        for (int a=0;a<1000;a++){
            YieldDemo1 demo1=new YieldDemo1();
            new  Thread(demo1).start();
        }

        synchronized (lock){
            lock.notifyAll();
        }
    }
}
