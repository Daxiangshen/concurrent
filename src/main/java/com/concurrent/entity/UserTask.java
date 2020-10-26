package com.concurrent.entity;

/**
 * UserTask class
 *
 * @author : yuxiang
 * @date : 2020/10/26
 */
public class UserTask implements Runnable{
    private int id;

    public UserTask(int id){
        this.id=id;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName()+"==>"+id);
    }
}
