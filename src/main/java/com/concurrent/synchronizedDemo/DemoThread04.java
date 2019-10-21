package com.concurrent.synchronizedDemo;

/**
 * DemoThread04 Class
 * 父子类可以锁重入
 * @author : yuxiang
 * @date : 2019/10/21
 */
public class DemoThread04 {
    public static void main(String[] args) {
        Thread t1=new Thread(() -> {
            Child sub=new Child();
            sub.runChild();
        });
        t1.start();
    }
}

class Parent{
    int i=10;
    synchronized void runParent(){
        try {
            i--;
            System.out.println("Parent>>>>i"+i);
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Child extends Parent{
    synchronized void runChild(){
        try {
            while (i>0){
                i--;
                System.out.println("Child>>>>i"+i);
                Thread.sleep(100);
                //调用父类中的synchronized方法不会引起死锁
                this.runParent();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
