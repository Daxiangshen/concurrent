package com.concurrent.joinDemo;

/**
 * <h3>JoinDemo0  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-19 15:01
 **/
public class JoinDemo0 {
    public static void main(String[] args) {
        Thread t1=new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+"  run over!");
        });

        Thread t2=new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+"  run over!");
        });

        t1.start();
        t2.start();

        System.err.println(Thread.currentThread().getName()+"  wait"+t1.getName()+"  and"+t2.getName()+"  run over!");

//        try {
//            //等待线程1执行完毕
//            t1.join();
//            //等待线程2执行完毕
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            //等待线程1执行完毕。参数还有纳秒
            t1.join(1000,500);
            //等待线程2执行完毕
            t2.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("final  "+t1.getName()+"  and"+t2.getName()+"  run over!");

        //查看线程状态
        System.err.println("t1's state  "+t1.getState());
        System.err.println("t2's state  "+t2.getState());
    }
}
