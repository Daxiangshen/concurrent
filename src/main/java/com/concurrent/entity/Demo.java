package com.concurrent.entity;

/**
 * Demo class
 *
 * @author : yuxiang
 * @date : 2020/11/17
 */
public class Demo implements Comparable<Demo>{

    private Integer id;
    private String name;

    public Demo(Integer id,String name){
        super();
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Demo[id="+id+",name="+name+"]";
    }

    @Override
    public int compareTo(Demo o) {
        if(this.id<o.id){
            return -1;
        }else if(this.id>o.id){
            return 1;
        }else {
            return 0;
        }
     }
}
