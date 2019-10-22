package com.concurrent.synchronizedDemo;

/**
 * DemoThread06 Class
 * 同步代码块
 * 当前对象,类,任意对象
 * 同类型锁之间互斥,不同类型的锁之间互不干扰
 * @author : yuxiang
 * @date : 2019/10/22
 */
public class DemoThread06 {
    public void run1(){
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName()+">当前对象锁..");
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void run2(){
        synchronized (DemoThread06.class){
            try {
                System.out.println(Thread.currentThread().getName()+">类锁..");
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private Object objectLock=new Object();
    public void run3(){
        synchronized (objectLock){
            try {
                System.out.println(Thread.currentThread().getName()+">任意对象锁..");
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //测试方法
    public static void test(final int type){
        switch (type){
            case 1:
                System.out.println("当前对象锁测试...");
                break;
            case 2:
                System.out.println("类锁测试...");
                break;
            case 3:
                System.out.println("任意对象锁测试...");
                break;
        }
        final DemoThread06 demo1=new DemoThread06();
        final DemoThread06 demo2=new DemoThread06();
        Thread t1=new Thread(() -> {
            switch (type){
                case 1:
                    demo1.run1();
                    break;
                case 2:
                    demo1.run2();
                    break;
                case 3:
                    demo1.run3();
                    break;
            }
        },"t1");
        Thread t2=new Thread(() -> {
            switch (type){
                case 1:
                    demo1.run1();
                    break;
                case 2:
                    demo1.run2();
                    break;
                case 3:
                    demo1.run3();
                    break;
            }
        },"t2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        //test(1);
        //test(2);
        //test(3);
        final DemoThread06 demo1=new DemoThread06();
        final DemoThread06 demo2=new DemoThread06();

        /**
         * 同类型锁之间互斥。不同类型锁之间互不干扰
         * */
        Thread t1=new Thread(demo1::run2,"t1");
        t1.start();

        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread t2=new Thread(demo2::run1,"t2");
        t2.start();
    }
}
