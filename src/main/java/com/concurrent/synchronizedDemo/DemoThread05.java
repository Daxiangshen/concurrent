package com.concurrent.synchronizedDemo;

/**
 * DemoThread05 Class
 * 抛出异常释放锁
 * @author : yuxiang
 * @date : 2019/10/21
 */
public class DemoThread05 {
    private int i=0;

    public synchronized void run(){
        while (true){
            i++;
            System.out.println(Thread.currentThread().getName()+"-run>i="+i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (i==10){
                throw new RuntimeException();
            }
        }
    }
    public synchronized void get(){
        System.out.println(Thread.currentThread().getName()+"-get>i="+i);
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread05 demoThread05=new DemoThread05();
        new Thread(demoThread05::run,"t1").start();

        //保证t1线程先执行
        Thread.sleep(1000);

        new Thread(demoThread05::get,"t2").start();
    }
}
