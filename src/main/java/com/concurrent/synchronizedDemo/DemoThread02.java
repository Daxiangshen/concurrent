package com.concurrent.synchronizedDemo;

/**
 * <h3>DemoThread02  Class</h3>
 * synchronized关键字的脏读  如果都加synchronized关键字，会出现只有set之后才能get的现象。也体现了对象锁。对象中的synchronized方法会同步执行
 * @author : YuXiang
 * @date : 2019-09-23 14:18
 **/
public class DemoThread02 {
    private String name="张三";

    private String address="太原";

    public synchronized void setVal(String name,String address){
        this.name=name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.address=address;
        System.out.println("setVal最终结果:username="+name+",address="+address);
    }

    public /*synchronized*/ void getVal(){
        System.out.println("getVal方法得到:username="+this.name+",address="+this.address);
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread02 dr=new DemoThread02();
        Thread t1=new Thread(() -> {
            dr.setVal("李四","北京");
        });

        t1.start();
        Thread.sleep(1000);
        dr.getVal();
    }
}
