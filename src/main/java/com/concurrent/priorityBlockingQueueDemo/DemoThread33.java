package com.concurrent.priorityBlockingQueueDemo;

import com.concurrent.entity.Demo;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * DemoThread33 class
 *
 * 一个无界阻塞队列，它使用与类 PriorityQueue 相同的顺序规则，并且提供了阻塞获取操作。虽然此队列逻辑上是无界的，但是资源被耗尽时试图执行 add 操作也将失败（导致 OutOfMemoryError）。
 * 此类不允许使用 null 元素。依赖自然顺序的优先级队列也不允许插入不可比较的对象（这样做会导致抛出 ClassCastException）。
 * 此类及其迭代器可以实现 Collection 和 Iterator 接口的所有可选 方法。
 * iterator() 方法中提供的迭代器并不 保证以特定的顺序遍历 PriorityBlockingQueue 的元素。
 * 如果需要有序地进行遍历，则应考虑使用 Arrays.sort(pq.toArray())。
 * 此外，可以使用方法 drainTo 按优先级顺序移除 全部或部分元素，并将它们放在另一个 collection 中。
 * 在此类上进行的操作不保证具有同等优先级的元素的顺序。如果需要实施某一排序，那么可以定义自定义类或者比较器，比较器可使用修改键断开主优先级值之间的联系。
 * 例如，以下是应用先进先出 (first-in-first-out) 规则断开可比较元素之间联系的一个类。
 * 要使用该类，则需要插入一个新的 FIFOEntry(anEntry) 来替换普通的条目对象。
 *
 * 注意：加入到PriorityBlockingQueue中的元素不是立即排序的,是在调用take等读取方法之后
 * ----------------------------------------------
 * put、add方法实际调用了offer方法
 * ----------------------------------------------
 * peek 读取头元素不移除，队列为空,返回null,不阻塞, 不抛异常
 * poll 读取头元素并移除，队列为空,返回null,不阻塞, 不抛异常
 * poll 可指定阻塞时间,2秒,如果队列依然为空,则返回null,不抛异常
 * take 读取头元素并移除,如果队列为空,则永远阻塞,不抛出异常
 * drainTo 取出queue中指定个数（或全部）的元素放入list中,并移除，当队列为空时不抛出异常
 *
 * @author : yuxiang
 * @date : 2020/11/17
 */
public class DemoThread33 {

    /**
     * 测试添加方法
     * */
    public static void testAdd(){
        PriorityBlockingQueue<Demo> queue=new PriorityBlockingQueue<Demo>(12);
        queue.add(new Demo(3,"a"));
        queue.offer(new Demo(1,"b"));
        //queue.add(null);  //不允许添加null元素
        queue.put(new Demo(2,"c")); //实际调用了offer方法
        queue.offer(new Demo(4,"d"),1, TimeUnit.SECONDS); //设定阻塞时间
        System.out.println("队列中的数据不是按照顺序排列的:"+queue);
    }

    public static void testTake1(){
        PriorityBlockingQueue<Demo> queue=new PriorityBlockingQueue<Demo>(12);
        queue.add(new Demo(3,"a")); //实际调用了offer方法
        queue.offer(new Demo(1,"b"));
        //queue.add(null); //不允许添加null元素
        queue.put(new Demo(2,"c")); //实际调用了offer方法
        queue.offer(new Demo(4,"d"),1,TimeUnit.SECONDS); //设定阻塞时间
        System.out.println(queue);
        try {
            System.out.println("take1>>"+queue.take());
            System.out.println(queue); //take之后才进行排序
            System.out.println("poll>>"+queue.poll());
            System.out.println("take2>>"+queue.take());
            System.out.println("take3>>"+queue.take());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void testTake2(){
        PriorityBlockingQueue<Demo> queue=new PriorityBlockingQueue<>();
        queue.add(new Demo(3,"a")); //实际调用了offer方法
        queue.offer(new Demo(1,"b"));
        System.out.println("a>>"+queue );
        System.out.println("b>>"+queue.peek()); //读取头元素不移除
        System.out.println("c>>"+queue);
        System.out.println("d>>"+queue.poll()); //读取头元素并移除
        System.out.println("e>>"+queue);
        try {
            //获取头元素并获取数据
            System.out.println("f>>"+queue.take());
            System.out.println("g>>"+queue);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("h>>"+queue.peek()); //队列为空,返回null,不阻塞,不抛异常
        System.out.println("i>>"+queue.poll()); //队列为空,返回null,不阻塞,不抛异常

        try {
            System.out.println("j>>"+queue.poll(2,TimeUnit.SECONDS));
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            queue.take(); //如果队列为空,则永远阻塞,不抛出异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //testAdd();
        //testTake1();
        testTake2();
    }
}
