package com.concurrent.createThreadDemo;

import com.concurrent.entity.Account;

/**
 * <h3>CreateThreadImplementsRunnable  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-09 17:30
 **/
public class CreateThreadImplementsRunnable implements Runnable{

    private Account account;

    public CreateThreadImplementsRunnable(){}

    public CreateThreadImplementsRunnable(Account account){
        this.account=account;
    }

    public void setAccount(Account account){
        this.account=account;
    }

    @Override
    public void run() {
        System.err.println(Thread.currentThread().getName()+"  Account's information :  "+this.account);
    }

    public static void main(String[] args) {
        Account account=new Account("919191","yuXiang",true);
        CreateThreadImplementsRunnable thread=new CreateThreadImplementsRunnable();
        thread.setAccount(account);
        new Thread(thread).start();

        CreateThreadImplementsRunnable thread1=new CreateThreadImplementsRunnable(account);
        new Thread(thread1).start();

        new Thread(() -> System.err.println(account)).start();
    }
}
