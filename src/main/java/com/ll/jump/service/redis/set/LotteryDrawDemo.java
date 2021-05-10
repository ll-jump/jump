package com.ll.jump.service.redis.set;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author LiLin
 * @desc 抽奖demo
 * @create 2021-04-30 16:29
 **/
public class LotteryDrawDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 添加参与抽奖的人员
     * @param userId
     * @param lotteryDrawEventId
     */
    public void addLotteryDrawUser(long userId, long lotteryDrawEventId){
        jedis.sadd("lottery_draw:" + lotteryDrawEventId + ":users", String.valueOf(userId));
    }

    /**
     * 抽奖（可重复中奖）
     * @param lotteryDrawEventId
     * @return
     */
    public List<String> doLotteryDrawCanRepeat(long lotteryDrawEventId, int count){
        return jedis.srandmember("lottery_draw:" + lotteryDrawEventId + ":users", count);
    }

    /**
     * 抽奖（不可重复中奖）
     * @param lotteryDrawEventId
     * @return
     */
    public Set<String> doLotteryDrawNoRepeat(long lotteryDrawEventId, int count){
        return jedis.spop("lottery_draw:" + lotteryDrawEventId + ":users", count);
    }

    public static void main(String[] args) {
        LotteryDrawDemo demo = new LotteryDrawDemo();
        long lotteryDrawEventId = 1;
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.del("lottery_draw:" + lotteryDrawEventId + ":users");
        //维护参与抽奖人员
        for (int i = 0; i < 20; i++) {
            demo.addLotteryDrawUser(i + 1, lotteryDrawEventId);
        }
        //各等奖不可重复中奖
        //抽取一等奖 1个
        Set<String> luckOne = demo.doLotteryDrawNoRepeat(lotteryDrawEventId, 1);
        System.out.println("一等奖中奖名单：" + luckOne);
        //抽取二等奖 3个
        Set<String> luckTwo = demo.doLotteryDrawNoRepeat(lotteryDrawEventId, 3);
        System.out.println("二等奖中奖名单：" + luckTwo);
        //抽取三等奖 5个
        Set<String> luckThree = demo.doLotteryDrawNoRepeat(lotteryDrawEventId, 5);
        System.out.println("三等奖中奖名单：" + luckThree);

        //各等奖可重复中奖
        //抽取一等奖 1个
        List<String> luckOneRepeat = demo.doLotteryDrawCanRepeat(lotteryDrawEventId, 1);
        System.out.println("一等奖中奖名单：" + luckOneRepeat);
        //抽取二等奖 3个
        List<String> luckTwoRepeat = demo.doLotteryDrawCanRepeat(lotteryDrawEventId, 3);
        System.out.println("二等奖中奖名单：" + luckTwoRepeat);
        //抽取三等奖 5个
        List<String> luckThreeRepeat = demo.doLotteryDrawCanRepeat(lotteryDrawEventId, 5);
        System.out.println("三等奖中奖名单：" + luckThreeRepeat);
    }
}
