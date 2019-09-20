package com.concurrent.interruptDemo;

/**
 * <h3>InterruptDemo0  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-20 14:27
 **/
public class InterruptDemo0 {
    public static void main(String[] args) {
        Thread t1=new Thread(() -> {
            for (int a=0;a<999999;a++){
                //判断中断标记，默认为false
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+" interrupt");
                    break;
                }
                System.out.println(Thread.currentThread().getName()+a+"  is running");
            }
        });

        t1.start();

        //中断标记设置为true.只是标记，不做动作。不会打断执行。  如需做操作，则在代码中用isInterrupted方法判断
        t1.interrupt();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
    }
}
