package com.concurrent.mapDemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * DemoThread22 Class
 * 并发容器构建
 *
 * ConcurrentHashMap ->替代HashMap,HashTable
 * ConcurrentSkipListMap ->替代TreeMap
 * map.put与map.putIfAbsent的区别
 * @author : yuxiang
 * @date : 2019/11/20
 */
class DemoDemo{
    public DemoDemo(){}
}
public class DemoThread22 {
    public static void testMap1(){
        //通过并发下的运行时间对比ConcurrentHashMap与Hashtable的性能
        final ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
        //final Hashtable<String,Integer> map=new Hashtable<>();
        for (int i=0;i<10;i++){
            new Thread(() -> {
                Long start=System.currentTimeMillis();
                for (int a=0;a<1000000;a++){
                    map.put("a"+a,a);
                }
                System.out.println(System.currentTimeMillis()-start);
            }).start();
        }
    }

    public static void testSkipListMap1() throws InterruptedException{
        //高性能线程安全
        //final ConcurrentSkipListMap<String,DemoDemo> skipMap=new ConcurrentSkipListMap<>();
        //线程不安全,性能高
        //final SortedMap<String,DemoDemo> skipMap=new TreeMap<>();
        //低性能,线程安全
        final SortedMap<String,DemoDemo> skipMap= Collections.synchronizedSortedMap(new TreeMap<>());
        final CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i=0;i<10;i++){
            new Thread(() -> {
                Long start=System.currentTimeMillis();
                Random rn=new Random();
                for (int a=0;a<1000;a++){
                    try {
                        skipMap.put("k"+a%10,new DemoDemo());
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("添加100个元素耗时"+(System.currentTimeMillis()-start)+"毫秒");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(skipMap.size());
    }

    public static void testMap2(){
        ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
        map.put("a",1);
        map.put("b",1);
        map.put("c",1);
        map.put("d",1);
        //如果key已经存在则更新
        map.put("a",2);
        System.out.println(map);
        //如果key存在则不更新,不存在则添加
        map.putIfAbsent("b",2);
        map.putIfAbsent("e",3);
        System.out.println(map);
    }

    public static void main(String[] args) throws InterruptedException {
        //testMap1();
        //testSkipListMap1();
        testMap2();
    }
}
