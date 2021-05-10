package com.ll.jump.service.redis.set;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author LiLin
 * @desc 朋友圈点赞demo
 * @create 2021-04-30 14:40
 **/
public class MomentsDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 点赞朋友圈
     * @param momentId
     * @param userId
     */
    public void likeMoment(long momentId, long userId){
        jedis.sadd("moment_like_users:" + momentId, String.valueOf(userId));
    }

    /**
     * 取消点赞朋友圈
     * @param momentId
     * @param userId
     */
    public void disLikeMoment(long momentId, long userId){
        jedis.srem("moment_like_users:" + momentId, String.valueOf(userId));
    }

    /**
     * 查看某人是否点赞某朋友圈
     */
    public boolean hasLikeMoment(long momentId, long userId){
        return jedis.sismember("moment_like_users:" + momentId, String.valueOf(userId));
    }

    /**
     * 获取朋友圈点赞人数
     * @param momentId
     * @return
     */
    public long getLikeMomentUserCount(long momentId){
        return jedis.scard("moment_like_users:" + momentId);
    }

    /**
     * 获取朋友圈点赞用户集合
     * @param momentId
     * @return
     */
    public Set<String> getLikeMomentUsers(long momentId){
        return jedis.smembers("moment_like_users:" + momentId);
    }

    public static void main(String[] args) {
        //我的用户id
        long userId = 1;
        //我的朋友圈id
        long momentId = 120;
        //我的朋友1用户id
        long friendUserId1 = 2;
        //我的朋友2用户id
        long friendUserId2 = 3;
        //朋友1点赞我的朋友圈
        MomentsDemo demo = new MomentsDemo();
        demo.likeMoment(momentId, friendUserId1);
        //朋友1取消点赞我的朋友圈
        demo.disLikeMoment(momentId, friendUserId1);
        //获取朋友1是否对我的朋友圈点赞
        boolean hasLikeMoment = demo.hasLikeMoment(momentId, friendUserId1);
        System.out.println("朋友1是否点赞我的朋友圈:" + (hasLikeMoment ? "是" : "否"));

        //朋友2点赞我的朋友圈
        demo.likeMoment(momentId, friendUserId2);
        //获取朋友2是否对我的朋友圈点赞
        hasLikeMoment = demo.hasLikeMoment(momentId, friendUserId2);
        System.out.println("朋友2是否点赞我的朋友圈:" + (hasLikeMoment ? "是" : "否"));

        //我自己查看我的朋友圈点赞情况
        long likeCount = demo.getLikeMomentUserCount(momentId);
        Set<String> likeUserSet = demo.getLikeMomentUsers(momentId);
        System.out.println("我的朋友圈有" + likeCount + "个人点赞，点赞人为：" + likeUserSet.toString());
    }
}
