package com.concurrent.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import javax.annotation.Resource;

/**
 * RedissonTest class
 *
 * @author : yuxiang
 * @date : 2020/11/20
 */
public class RedissonTest {

    @Resource
    private Redisson redisson;

    public String lock(){
        String key="test";
        RLock lock=redisson.getLock(key);
        try {
          lock.lock();
          //减库存操作


        }finally {
            lock.unlock();
        }
        return null;
    }
}
