package com.xjx.springboot.util;

public class redisLockTherad extends Thread {
	@Override
    public void run() {
        System.out.println("开始吃饭?...\t" + new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束吃饭?...\t" + new Date());
    }

}
