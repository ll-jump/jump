package com.ll.jump.service.redis.bitmap;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiLin
 * @desc 通过redis位图实现用户操作日志demo
 * @create 2021-05-08 09:44
 **/
public class UserOperationLogDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 新增用户操作日志
     * @param operation
     * @param userId
     */
    public void addUserOperationLog(String operation, long userId){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        //如果日活跃用户数量过大，可以对userId进行分片存储
        //redis中bit映射被限制在512MB之内，所以最大是2^32位。建议每个key的位数都控制下，因为读取时候时间复杂度O(n)，越大的串读的时间花销越多。
        long slice = userId / 100000;
        long offset = userId % 100000;
        jedis.setbit("userOperation:" + operation + ":" + today + ":" + slice + ":log", offset, "1");
    }

    /**
     * 用户今日是否有该操作
     * @param operation
     * @param userId
     * @return
     */
    public boolean hasOperation(String operation, long userId){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        //如果日活跃用户数量过大，可以对userId进行分片存储
        long slice = userId / 100000;
        long offset = userId % 100000;
        return jedis.getbit("userOperation:" + operation + ":" + today + ":" + slice + ":log", offset);
    }

    public static void main(String[] args) {
        UserOperationLogDemo demo = new UserOperationLogDemo();
        //添加用户操作日志
        demo.addUserOperationLog("操作1", 12);
        demo.addUserOperationLog("操作1", 100011);
        //获取用户是否有该操作
        boolean has = demo.hasOperation("操作1", 12);
        System.out.println("用户12是否有操作1:" + has);
        has = demo.hasOperation("操作1", 100012);
        System.out.println("用户100012是否有操作1:" + has);
        has = demo.hasOperation("操作1", 100011);
        System.out.println("用户100011是否有操作1:" + has);
        has = demo.hasOperation("操作2", 100011);
        System.out.println("用户100011是否有操作2:" + has);
    }
}
