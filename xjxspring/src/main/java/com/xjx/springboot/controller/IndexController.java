package com.xjx.springboot.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjx.springboot.xjxspring.RedisLock;

@Controller
public class IndexController {

    @Autowired
    RedisLock redisLock;
    
    int count = 0;
    
    @RequestMapping("/index")
    @ResponseBody
    public String index() throws InterruptedException {

        int clientcount =1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientcount;i++){
        	executorService.execute(command);
            executorService.execute(new Runnable() {
				public void run() {
					  //通过Snowflake算法获取唯一的ID字符串
	                String id = IdUtil.getId();
	                try {
	                    redisLock.lock(id);
	                    count++;
	                }finally {
	                    redisLock.unlock(id);
	                }
	                countDownLatch.countDown();
				}
			});
            
              
          
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        logger.info("执行线程数:{},总耗时:{},count数为:{}",clientcount,end-start,count);
        return "Hello";
    }
}