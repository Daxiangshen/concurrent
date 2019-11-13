package com.concurrent.volatileDemo;

import com.concurrent.ThreadCommunication.DemoThread11;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoThread14 Class
 * volatile关键字可见性。不使用wait/notify进行线程通信
 * @author : yuxiang
 * @date : 2019/11/13
 */
public class DemoThread14 {
    private List<String> list=new ArrayList<>();
    private volatile boolean canGet=false;

    public void put(){
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("A");
            System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");
            if (i==5){
                //循环到第五次通知其它线程开始获取数据进行处理
                canGet=true;
                System.out.println("线程"+Thread.currentThread().getName()+"发出通知");
            }
        }
    }

    public void get(){
        while (true){
            if (canGet){
                for (String s:list){
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素:"+s);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread14 demo=new DemoThread14();

        new Thread(demo::put,"t1").start();
        new Thread(demo::get,"t2").start();
    }
}
