package com.concurrent.createThreadDemo;

import com.concurrent.entity.Account;
import com.sun.media.sound.SoftTuning;

/**
 * <h3>CreateThreadExtendThread  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-09 17:07
 **/
public class CreateThreadExtendThread extends Thread{
    private Account account;

    public CreateThreadExtendThread(){}

    public CreateThreadExtendThread(Account account){
        this.account=account;
    }

    public void setAccount(Account account){
        this.account=account;
    }

    public void run(){
        System.err.println(this.getName()+"  Account's information :  "+this.account+","+this.getState());
    }

    public static void main(String[] args) {
        Account account=new Account("909090","YuXiang",true);
        CreateThreadExtendThread thread=new CreateThreadExtendThread();
        thread.setAccount(account);
        System.err.println(thread.getState());
        thread.start();
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println(thread.getState());
        CreateThreadExtendThread thread1=new CreateThreadExtendThread(account);
        thread1.start();
    }
}
