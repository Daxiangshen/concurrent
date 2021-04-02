package com.concurrent.synchronousQueueDemo;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * DemoThread32 class
 *
 * BlockingQueue的实现类之synchronousQueue
 * 没有任何容量,必须先有线程从队列中take,才能向queue中add数据,否则会抛出队列已满的异常
 * 不能使用peek方法取数据,此方法底层没有实现,会直接返回null
 *
 * 如果没有读取线程,则add方法会排除Queue Full异常,所以建议使用put方法,进行阻塞
 * 如果没有写入线程,则poll方法会无法读取到数据,所以建议设置poll方法的阻塞时长,或者使用take方法进行永久阻塞
 *
 * @author : yuxiang
 * @date : 2020/11/2
 */
public class DemoThread32 {

    /**
     * 测试-SynchronousQueue没有容量,第一次add就会抛出异常
     * */
    public static void test1(){
        SynchronousQueue<Integer> queue=new SynchronousQueue<>();
        queue.add(1);
    }

    /**
     * 测试-SynchronousQueue没有容量,使用put方法阻塞,避免抛出异常
     * */
    public static void test2(){
        SynchronousQueue<Integer> queue=new SynchronousQueue<>();
        try {
            queue.put(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 测试:一个线程put一个线程take,如果没有数据则take线程永远阻塞
     * */
    public static void test3(){
        final SynchronousQueue<Integer> queue=new SynchronousQueue<>();
        new Thread(() -> {
            while (true){
                try {
                    //使用get方法当队列为空时阻塞
                    System.out.println(queue.take());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                //使用put方法,当队列满的时候阻塞
                queue.put(1);
                queue.put(2);
                queue.put(3);
                queue.put(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 测试:一个线程put一个线程take,take线程最多阻塞5秒,如果还没有取到数据,则结束线程
     * */
    public static void test4(){
        final SynchronousQueue<Integer> queue=new SynchronousQueue<>();
        new Thread(() -> {
            while (true){
                try {
                    //最多阻塞5s,如果还没有取到数据,则结束线程
                    Integer result=queue.poll(5, TimeUnit.SECONDS);
                    System.out.println("poll->"+result);
                    if(result==null){
                        System.out.println("poll->5s没有数据,线程stop");
                        break;
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                //使用put方法,当队列满的时候阻塞
                queue.put(1);
                queue.put(2);
                queue.put(3);
                queue.put(4);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }
}
