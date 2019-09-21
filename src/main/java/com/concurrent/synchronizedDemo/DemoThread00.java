package com.concurrent.synchronizedDemo;

/**
 * <h3>DemoThread00  Class</h3>
 * 对象锁和类锁
 * 多个对象多把锁，相互不影响
 * 访问静态变量则需要多个对象使用一把锁，那么需要将锁升级为类锁
 * synchronized是获得对象锁，如果作用在static类型上，则升级为类锁
 * @author : YuXiang
 * @date : 2019-09-21 14:30
 **/
public class DemoThread00 {
    private static int count=0;

    public synchronized static void add(){
        count++;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+">count="+count);
    }

    public static void main(String[] args) {

        final DemoThread00 thread1=new DemoThread00();
        final DemoThread00 thread2=new DemoThread00();

        Thread t1=new Thread(() -> {
            thread1.add();
            //thread2.add();
        },"thread1");

        Thread t2=new Thread(() -> {
            //thread1.add();
            thread2.add();
        },"thread2");

        t1.start();
        t2.start();
    }
}
