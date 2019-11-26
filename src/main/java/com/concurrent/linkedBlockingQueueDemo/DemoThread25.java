package com.concurrent.linkedBlockingQueueDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * DemoThread25 Class
 *
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

    public static void main(String[] args) {
        testAdd();
    }
}
