package com.concurrent.linkedQueueDemo;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * DemoThread23 Class
 * ConcurrentLinkedQueue
 * 无阻塞，无锁，高性能，无界队列(直至内存耗尽)，线程安全，性能优于BlockingQueue，不允许null值
 * 使用cas算法进行入队和出队操作
 *
 * 先进先出。场景：多个线程访问同一个集合中的元素
 * @author : yuxiang
 * @date : 2019/11/22
 */
public class DemoThread23 {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue=new ConcurrentLinkedQueue<>();
        queue.add(1);
        queue.add(2);//add方法实际调用了offer方法
        //offer方法与add没有区别
        queue.offer(3);
        queue.offer(4);
        //queue.add(null);//不允许添加null元素
        System.out.println(queue);

        System.out.println("peek="+queue.peek());//读取头元素，但是不移除
        System.out.println("size="+queue.size());//peek方法不会导致size改变

        System.out.println("poll="+queue.poll());//读取头元素，并且移除
        System.out.println("size="+queue.size());//poll方法导致size改变

        System.out.println("poll="+queue.poll());
        System.out.println("poll="+queue.poll());
        System.out.println("poll="+queue.poll());
        System.out.println("size="+queue.size());

        System.out.println("pool="+queue.peek());//队列为空，读取头元素，返回null
        System.out.println("pool="+queue.poll());//队列为空，读取头元素并移除，返回null
    }
}
