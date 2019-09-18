package com.concurrent.createThreadDemo;

import com.concurrent.entity.Account;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <h3>CreateThreadImplementsCallable  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-09-09 17:38
 **/
public class CreateThreadImplementsCallable implements Callable<Account> {

    private Account account;

    public CreateThreadImplementsCallable(){}

    public CreateThreadImplementsCallable(Account account){
        this.account=account;
    }

    public void setAccount(Account account){
        this.account=account;
    }

    @Override
    public Account call() throws Exception {
        System.err.println(Thread.currentThread().getName()+"  Account's information :  "+this.account);
        this.account.setValid(false);
        Thread.sleep(3000);
        return this.account;
    }

    public static void main(String[] args) {
        Account account=new Account("929292","yuXiang",true);
        CreateThreadImplementsCallable call=new CreateThreadImplementsCallable();
        call.setAccount(account);
        FutureTask<Account> futureTask=new FutureTask<Account>(call);
        new Thread(futureTask).start();

        try {
            Account result=futureTask.get();
            System.err.println("result: "+result);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}
