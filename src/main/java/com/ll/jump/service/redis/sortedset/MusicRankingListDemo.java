package com.ll.jump.service.redis.sortedset;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * @author LiLin
 * @desc 音乐排行榜demo
 * @create 2021-05-06 14:37
 **/
public class MusicRankingListDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 添加歌曲
     * @param songId
     */
    public void addSong(long songId){
        jedis.zadd("music_ranking_list", 0, String.valueOf(songId));
    }

    /**
     * 增加歌曲分数
     * @param songId
     * @param score
     */
    public void increaseSongScore(long songId, double score){
        jedis.zincrby("music_ranking_list", score, String.valueOf(songId));
    }

    /**
     * 获取歌曲排名
     * @param songId
     * @return
     */
    public long getSongRankingNumber(long songId){
        return jedis.zrevrank("music_ranking_list", String.valueOf(songId));
    }

    /**
     * 获取音乐排行榜
     * @return
     */
    public Set<Tuple> getMusitRankingList(){
        return jedis.zrevrangeWithScores("music_ranking_list", 0, 50);
    }

    public static void main(String[] args) {
        MusicRankingListDemo demo = new MusicRankingListDemo();
        for (int i = 0; i < 20; i++) {
            demo.addSong(i + 1);
        }

        demo.increaseSongScore(5, 3.2);
        demo.increaseSongScore(10, 2.1);
        demo.increaseSongScore(15, 5.1);

        long rankResult = demo.getSongRankingNumber(5);
        System.out.println("歌曲id为5的排名" + (rankResult + 1));

        Set<Tuple> musicRankingList = demo.getMusitRankingList();
        System.out.println("音乐排行榜:" + musicRankingList);

    }
}
