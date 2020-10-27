package com.concurrent.ratelimiter;

import com.concurrent.entity.UserReqVO;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/test1")
    public String test(){
        double acquire=rateLimiter1.acquire();
        System.out.println("ok........wait for"+acquire+"seconds");
        return "ok";
    }

    @PostMapping("/test2")
    public UserReqVO test(@RequestBody UserReqVO user){
        double acquire=rateLimiter2.acquire();
        System.out.println("deal data:"+user+"for wait"+acquire+"seconds");
        return user;
    }
}
