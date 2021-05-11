package com.concurrent.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.util.StringUtils;

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

    public String updateStock(){
        String key="test";
        RReadWriteLock rReadWriteLock=redisson.getReadWriteLock(key);
        RLock writeLock=rReadWriteLock.writeLock();
        writeLock.lock();
        System.out.println("获取写锁成功");
        //执行删减库存操作
        writeLock.unlock();
        System.out.println("释放写锁成功");
        return "end";
    }

    public String getStock() throws InterruptedException{
        String key="test";
        RReadWriteLock rReadWriteLock=redisson.getReadWriteLock(key);
        RLock rLock=rReadWriteLock.readLock();
        rLock.lock();
        System.out.println("获取读锁成功");
        //执行读取库存操作
        String stock="1";
        if(StringUtils.isEmpty(stock)){
            System.out.println("查询数据库库存为10");
            Thread.sleep(5000);
            //将库存设置为10
        }
        rLock.unlock();
        System.out.println("释放读锁成功");
        return "end";
    }
}
