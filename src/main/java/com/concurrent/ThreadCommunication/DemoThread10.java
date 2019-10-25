package com.concurrent.ThreadCommunication;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoThread10 Class
 *
 * 不使用wait/notify的通信
 *
 * @author : yuxiang
 * @date : 2019/10/24
 */
public class DemoThread10 {
    private volatile List<String> list=new ArrayList<String>();
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
                //循环到第五次则通知其它线程开始获取数据进行处理
                canGet=true;
                System.out.println("线程"+Thread.currentThread().getName()+"发出通知");
            }
        }
    }

    public void get(){
        while (true){
            if (canGet){
                for (String s:list){
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素"+s);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread10 demo=new DemoThread10();
        new Thread(demo::put,"t1").start();
        new Thread(demo::get,"t2").start();
    }
}
