package com.concurrent.synchronizedDemo;

/**
 * DemoThread07 Class
 * 不要在线程中修改锁对象的引用，引用被改变会导致锁失效
 * @author : yuxiang
 * @date : 2019/10/22
 */
public class DemoThread07 {
    private String lock="lock handler";

    private void method(){
        synchronized (lock){
            try {
                System.out.println("当前线程"+Thread.currentThread().getName()+"开始");
                //锁的引用被改变，则其它线程可获得锁，导致并发问题（修改string的值，开辟了新的内存空间，是新的对象，t2获取的是新对象的锁）
                lock="change lock handler";
                Thread.sleep(2000);
                System.out.println("当前线程"+Thread.currentThread().getName()+"结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread07 changeLock=new DemoThread07();
        Thread t1=new Thread(changeLock::method,"t1");
        Thread t2=new Thread(changeLock::method,"t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于锁的引用被改变，所以t2线程也进入到method方法内执行
        t2.start();
    }
}
