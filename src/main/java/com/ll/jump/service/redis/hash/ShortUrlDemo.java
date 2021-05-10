package com.ll.jump.service.redis.hash;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiLin
 * @desc 短链接点击数demo（redis hash数据结构的应用）
 * @create 2021-04-28 21:13
 **/
public class ShortUrlDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    private static final String[] X36_ARRAY = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

    public static void main(String[] args) {
        ShortUrlDemo shortUrlDemo = new ShortUrlDemo();
//        shortUrlDemo.testJedis();
        shortUrlDemo.testShortUrl("https://www.baidu.com?a=123");
    }

    public void testShortUrl(String url) {
        String shortUrl = getShortUrl(url);
        System.out.println("生成的短链接为:" + shortUrl);
        for (int i = 0; i < 10; i++) {
            clickShortUrl(shortUrl);
        }

        System.out.println(getShortUrlClickNumber(shortUrl));
    }

    /**
     * 获取短链接的点击数
     *
     * @param shortUrl
     * @return
     */
    public Long getShortUrlClickNumber(String shortUrl) {
        String num = jedis.hget("short_url_click_number", shortUrl);
        return Long.valueOf(num);
    }

    /**
     * 获取短链接
     *
     * @param url
     * @return
     */
    public String getShortUrl(String url) {
        Long seed = jedis.incr("short_url_seed");
        StringBuffer sBuffer = new StringBuffer();
        while (seed > 0) {
            sBuffer.append(X36_ARRAY[(int) (seed % 36)]);
            seed = seed / 36;
        }
        String shortUrl = sBuffer.reverse().toString();
        //短链接对应的点击数
        jedis.hset("short_url_click_number", shortUrl, "0");
        //短链接和长链接的对应关系
        jedis.hset("url_mapping", shortUrl, url);
        return shortUrl;
    }

    /**
     * 点击短链接
     *
     * @param shortUrl
     */
    public void clickShortUrl(String shortUrl) {
        jedis.hincrBy("short_url_click_number", shortUrl, 1);
    }

    public void testJedis() {
        jedis.set("k1", "v1");
        System.out.println(jedis.get("k1"));

        jedis.mset("k2", "v2", "k3", "v3");
        System.out.println(jedis.get("k3"));
        System.out.println(jedis.mget("k1", "k2", "k3"));

        Map<String, String> map = new HashMap<>();
        map.put("a1", "1");
        map.put("a2", "2");
        jedis.hset("hk1", map);
        System.out.println(jedis.hgetAll("hk1"));

        jedis.hincrBy("hk1", "a1", 1);
        System.out.println(jedis.hgetAll("hk1"));
    }


}
