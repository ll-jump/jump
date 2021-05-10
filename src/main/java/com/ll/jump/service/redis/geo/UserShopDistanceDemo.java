package com.ll.jump.service.redis.geo;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLin
 * @desc 用户与商家距离demo
 * @create 2021-05-08 10:38
 **/
public class UserShopDistanceDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 添加商家地理位置
     * @param name 用户或商家名称
     * @param longitude 经度
     * @param latitude 纬度
     */
    public void addShopLocation(String name, double longitude, double latitude){
        jedis.geoadd("location:shop", longitude, latitude, name);
    }

    /**
     * 获取两个地方的距离
     * @param member1
     * @param member2
     * @return
     */
    public double getDistance(String member1, String member2){
        return jedis.geodist("location:shop", member1, member2, GeoUnit.KM);
    }

    /**
     * 获取指定经纬度附近的商家
     * @param longitude
     * @param latitude
     * @param distance
     */
    public List<GeoRadiusResponse> getNearShop(double longitude, double latitude, double distance){
        return jedis.georadius("location:shop", longitude, latitude, distance, GeoUnit.KM);
    }

    public static void main(String[] args) {
        UserShopDistanceDemo demo = new UserShopDistanceDemo();
        //添加商铺地理位置
        demo.addShopLocation("庆丰包子铺", 116.452652,39.976015);
        demo.addShopLocation("头壹号油条铺", 116.241181,40.094055);

        //获取距离
        System.out.println("庆丰包子铺和头壹号油条铺的距离为" + demo.getDistance("庆丰包子铺", "头壹号油条铺") + "km");
        //张三的位置 116.241822,40.09223
        //获取张三附近5km的商铺
        List<GeoRadiusResponse> nearShopList = demo.getNearShop(116.241822,40.09223, 5);
        List<String> result = new ArrayList<>();
        for (GeoRadiusResponse geoRadiusResponse : nearShopList) {
            result.add(geoRadiusResponse.getMemberByString());
        }
        System.out.println("张三附近5km的商铺为：" + result.toString());
    }
}
