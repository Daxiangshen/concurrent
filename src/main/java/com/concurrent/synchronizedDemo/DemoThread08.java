package com.concurrent.synchronizedDemo;

/**
 * DemoThread08 Class
 * 在线程中修改锁对象的属性，而不修改引用则不会引起锁失效，不会产生线程安全问题
 * @author : yuxiang
 * @date : 2019/10/22
 */
class Person{
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
public class DemoThread08 {
    private Person person=new Person();

    public void changerUser(String name,String age){
        synchronized (person){
            System.out.println("线程"+Thread.currentThread().getName()+"开始"+person);
            person.setName(name);
            person.setAge(age);
            System.out.println("线程"+Thread.currentThread().getName()+"修改为"+person);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"结束"+person);
        }
    }

    public static void main(String[] args) {
        final DemoThread08 thread08=new DemoThread08();
        new Thread(() -> thread08.changerUser("小白","12"),"t1").start();
        new Thread(() -> thread08.changerUser("小黑","122"),"t2").start();
    }
}
