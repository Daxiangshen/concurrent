package com.concurrent.arrayBlockingQueueDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * DemoThread24 Class
 * BlockingQueue的实现类ArrayBlockingQueue
 * 基于数组实现的阻塞队列，创建时可指定长度，内部实现维护了一个定长度的数组用于缓存数据
 *
 * 内部不详cow容器，没有采用读写分离，add和poll不能同时进行，可以指定先进先出或者先进后出。使用ReentrantLock(重入锁)控制线程安全
 * offer:如果队列已经满了，则不阻塞，不抛出异常.offer可设置最大阻塞时间，2秒，如果队列还是满的，则不阻塞，不抛出异常
 * add:如果队列满了，则不阻塞，直接抛出异常
 * put:如果队列满了则永远阻塞，不抛出异常
 * peek:读取头元素，不移除，队列为空，返回null，不阻塞，不抛出异常
 * poll:读取头元素并移除，队列为空，返回null，不阻塞，不抛出异常.poll可指定阻塞时间，2秒，如果队列依然为空，则返回null，不抛异常
 * take:读取头元素并移除，如果队列为空，则永远阻塞，不抛出异常
 * drainTo:取出queue中指定个数(或全部)的元素放入list中，并移除，当队列为空时不抛出异常
 * @author : yuxiang
 * @date : 2019/11/22
 */
public class DemoThread24 {
    //测试各种添加元素的方法
    public static void testAdd(){
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(3);
        queue.add(1);
        queue.offer(2);
        //queue.add(null);//不允许添加null元素
        try {
            queue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //queue.add(4);//如果队列满了则抛出异常
        System.out.println(queue);
        queue.offer(4);//如果队列已经满了，则不阻塞，不抛出异常
        System.out.println(queue);
        try {
            //可设置最大阻塞时间,2秒,如果队列还是满的，则不阻塞，不抛出异常
            queue.offer(6,2, TimeUnit.SECONDS);
            System.out.println(queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //queue.add(7);//如果队列满了，则不阻塞，直接抛出异常

        try {
            queue.put(7);//如果队列满了，则永远阻塞，不抛出异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);
    }


    //测试获取数据
    public static void testTake1(){
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(2);
        queue.add(1);
        queue.add(2);

        System.out.println(queue.peek());//读取头元素不移除
        System.out.println(queue);

        System.out.println(queue.poll());//读取头元素并移除
        System.out.println(queue);

        try {
            //读取头元素并移除
            System.out.println(queue.take());
            System.out.println(queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue.peek());//队列为空，返回null，不阻塞，不抛异常
        System.out.println(queue.poll());//队列为空，返回null，不阻塞，不抛异常
        try {
            //可指定阻塞时间,2秒,如果队列依然为空,则返回null,不抛异常
            System.out.println(queue.poll(2,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            queue.take();//如果队列为空，则永远阻塞，不抛出异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试获取数据2
    public static void testTake2(){
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        List<Integer> list=new ArrayList<>();
        queue.drainTo(list,2);//取出queue中指定个数的元素放入list中，并移除
        System.out.println(list);
        System.out.println(queue);
    }

    public static void testTake3(){
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        List<Integer> list=new ArrayList<>();
        queue.drainTo(list);//取出queue中全部元素放入list中，并移除
        System.out.println(list);
        System.out.println(queue);

        List<Integer> list1=new ArrayList<>();
        queue.drainTo(list1);//当队列为空时不抛出异常
        System.out.println(list1);
        System.out.println(queue);
    }

    public static void main(String[] args) {
        //testAdd();
        //testTake1();
        //testTake2();
        testTake3();
    }
}
