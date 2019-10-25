package com.concurrent.ThreadCommunication;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoThread11 Class
 *
 * 基于wait/notify的线程通信  要和synchronized关键字一起使用！
 *
 * @author : yuxiang
 * @date : 2019/10/24
 */
public class DemoThread11 {
    //原子类
    private volatile List<String> list=new ArrayList<String>();
    private Object lock=new Object();

    public void put(){
        synchronized (lock){
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("A");
                System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");
                if (list.size()==5){
                    //数据准备好，发出通知，但是不释放锁
                    lock.notify();
                    System.out.println("发出通知");
                }
            }
        }
    }
    public void get(){
        synchronized (lock){
            try {
                System.out.println("线程"+Thread.currentThread().getName()+"业务处理，发现有需要的数据没准备好，则发起等待");
                System.out.println("线程"+Thread.currentThread().getName()+"wait");
                lock.wait();//wait释放锁，否则其它线程无法进入put方法
                System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
                for (String s:list){
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素"+s);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread11 demo=new DemoThread11();
        new Thread(demo::get,"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(demo::put,"t2").start();
    }
}
