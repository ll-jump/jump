package com.ll.jump.service.redis.set;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiLin
 * @desc 用户访问量 demo 一个用户多次访问算一次访问量
 * @create 2021-04-29 21:57
 **/
public class UVDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);
    /**
     * 新增用户访问
     * @param userId
     */
    public void addUserView(long userId){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());
        jedis.sadd("uv:" + today, String.valueOf(userId));
    }

    /**
     * 获取今日uv
     * @return
     */
    public long getTodayUv(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());
        return jedis.scard("uv:" + today);
    }

    public static void main(String[] args) {
        UVDemo uvDemo = new UVDemo();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                uvDemo.addUserView(i);
            }
        }
        System.out.println("今日UV：" + uvDemo.getTodayUv());
    }
}
