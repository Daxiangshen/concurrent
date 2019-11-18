package com.concurrent.actomicDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DemoThread16 Class
 * Atomic类可以保证共享变量的原子性
 * @author : yuxiang
 * @date : 2019/11/13
 */
public class DemoThread16 implements Runnable{

    //原子类
    private static AtomicInteger sum=new AtomicInteger(0);


    public void add(){
        System.out.println(Thread.currentThread().getName()+"初始sum="+sum);
        for (int i=0;i<10000;i++){
            //加完后得到返回值
            sum.addAndGet(1);
        }
        System.out.println(Thread.currentThread().getName()+"计算后sum="+sum);
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {
        //10个线程并发调用,最终结果应为100000
        ExecutorService es= Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            es.submit(new DemoThread16());
        }
        es.shutdown();
        while (true){
            if (es.isTerminated()){
                System.out.println("sum最终="+sum);
                if (sum.get()==100000){
                    System.out.println(sum+"=ok");
                }else {
                    System.out.println(sum+"=no");
                }
                break;
            }
        }
    }
}
