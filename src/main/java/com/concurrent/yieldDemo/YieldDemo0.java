package com.concurrent.yieldDemo;

/**
 * <h3>YieldDemo0  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-20 09:35
 **/
public class YieldDemo0 implements Runnable{

    @Override
    public void run() {
        System.err.println(Thread.currentThread().getName()+"  yield");
        Thread.yield();
        System.err.println(Thread.currentThread().getName()+"  run over!");
    }

    public static void main(String[] args) {
        for (int a=0;a<1000;a++){
            YieldDemo0 demo0=new YieldDemo0();
            new Thread(demo0).start();
        }
    }
}
