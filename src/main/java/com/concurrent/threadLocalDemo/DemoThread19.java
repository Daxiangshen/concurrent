package com.concurrent.threadLocalDemo;

/**
 * DemoThread19 Class
 * 使用ThreadLocal
 * @author : yuxiang
 * @date : 2019/11/18
 */
public class DemoThread19 {
    public static void main(String[] args) throws InterruptedException{
        final ThreadLocal<Integer> th=new ThreadLocal<Integer>();

        new Thread(() -> {
            try {
                th.set(10);
                System.out.println("t1 set th="+th.get());
                Thread.sleep(2000);
                System.out.println("t1 get th="+th.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Integer ele=th.get();
            System.out.println("t2 get th="+ele);
            th.set(200);
            System.out.println("t2 get th="+th.get());
        }).start();
    }
}
