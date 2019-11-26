package com.concurrent.linkedBlockingQueueDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * DemoThread25 Class
 * LinkedBlockingQueue  可以有界也可以无界   支持同时读和取
 * 其它操作和ArrayBlockingQueue 是一样的
 * @author : yuxiang
 * @date : 2019/11/26
 */
public class DemoThread25 {

    public static void testAdd(){
        LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>(3);
        queue.add(1);
        queue.offer(2);
        //queue.add(null);  //不允许添加null元素
        try {
            queue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);
        queue.offer(4);//如果队列满了，则不阻塞，不抛出异常
        System.out.println(queue);
        try {
            //可设置最大阻塞时间，2秒，如果队列还是满的，则不阻塞，不抛出异常
            queue.offer(6,2, TimeUnit.SECONDS);
            System.out.println(queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //queue.add(7);  //如果队列满了，则不阻塞，直接抛出异常
        try {
            queue.put(7);//如果队列满了，则永远阻塞，不抛出异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(queue);
    }

    public static void testTake1(){
        LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>(2);
        queue.add(1);
        queue.add(2);

        System.out.println(queue.peek());//读取头元素不移除
        System.out.println(queue);

        System.out.println(queue.poll());//读取头元素，并移除
        System.out.println(queue);

        try {
            //获取头元素并移除数据
            System.out.println(queue.take());
            System.out.println(queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue.peek());//队列为空，返回null，不阻塞，不抛异常
        System.out.println(queue.poll());//队列为空，返回null，不阻塞，不抛异常

        try {
            //可指定阻塞时间，2秒，如果队列依然为空，则返回null，不抛异常
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

    public static void testTake2(){
        LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        List<Integer> list=new ArrayList<>();
        queue.drainTo(list,2);
        System.out.println(list);
        System.out.println(queue);
    }

    public static void testTake3(){
        LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        List<Integer> list=new ArrayList<>();
        queue.drainTo(list);//取出queue中的全部元素放入list中，并移除
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
