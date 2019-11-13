package com.concurrent.volatileDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DemoThread15 Class
 *
 * @author : yuxiang
 * @date : 2019/11/13
 */
public class DemoThread15 implements Runnable{
    public static volatile int sum=0;

    public static void add(){
        System.out.println(Thread.currentThread().getName()+"初始sum="+sum);
        for (int i=0;i<10000;i++){
            sum++;
        }
        System.out.println(Thread.currentThread().getName()+"计算后sum="+sum);
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {
        //如果volatile具有原子性,那么10个线程并发调用,最终结果应为100000
        ExecutorService es= Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            es.submit(new DemoThread15());
        }
        es.shutdown();
        while (true){
            if (es.isTerminated()){
                System.out.println("sum最终="+sum);
                if (sum==100000){
                    System.out.println(sum+"=ok");
                }else {
                    System.out.println(sum+"=no");
                }
                break;
            }
        }
    }
}
