package com.concurrent.ratelimiter;

import com.concurrent.entity.UserReqVO;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TestController class
 *
 * @author : yuxiang
 * @date : 2020/10/26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    RateLimiter rateLimiter1=RateLimiter.create(1);
    RateLimiter rateLimiter2=RateLimiter.create(2);
    RateLimiter rateLimiter3=RateLimiter.create(30);

    ConcurrentHashMap<String,RateLimiter> users=new ConcurrentHashMap<>();

    @GetMapping("/test1")
    public String test(){
        String result;
        //double acquire=rateLimiter1.acquire();
        boolean tryAcquire=rateLimiter1.tryAcquire();
        if(tryAcquire){
            result="ok";
            System.out.println("ok.......");
        }else {
            result="refuse";
            System.out.println("refuse");
        }
        return result;
    }

    @PostMapping("/test2")
    public UserReqVO test(@RequestBody UserReqVO user){
        String result;
        String name=user.getName();
        RateLimiter rl=null;
        if(users.get(name)==null){
            if("111".equals(name)){
                rl=RateLimiter.create(1);
            }else {
                rl=RateLimiter.create(0.5);
            }
            users.put(name,rl);
        }else {
            rl= users.get(name);
        }
        double acquire=rl.acquire();
        System.out.println("相同用户限流"+acquire+"秒");
        return user;
    }
}
