package com.ll.jump.service.redis.hyperloglog;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LiLin
 * @desc 基于hyperLogLog的UV demo
 * 基于set的去重UV太耗内存，hyperLogLog基于一个概率算法，可实现0.8%左右的误差范围的去重统计，一个hyperLogLog数据结构大概只占12kb的内存
 * @create 2021-05-06 16:28
 **/
public class HyperLogLogUVDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    /**
     * 初始化指定日期UV数据
     */
    public void initUVData(String date) {
        Random random = new Random();
        int startIndex = random.nextInt(1000);
        System.out.println("Date为" + date + "的员工id起始index为：" + startIndex);
        jedis.del("hyperloglog_uv:" + date);
        for (int i = startIndex; i < startIndex + 1360; i++) {
            jedis.pfadd("hyperloglog_uv:" + date, String.valueOf(i + 1));
        }
        Long size = jedis.strlen("hyperloglog_uv:" + date);
        System.out.println("Date为" + date + "初始化的UV为" + getTodayUV(date) + ";占用空间大小为：" + size);
    }

    /**
     * 获取今日UV
     *
     * @return
     */
    public long getTodayUV(String date) {
        return jedis.pfcount("hyperloglog_uv:" + date);
    }

    /**
     * 获取指定时间间隔的UV
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public long getUV(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        List<String> dateList = new ArrayList<>();
        while (calendar.getTime().before(endDate)) {
            dateList.add("hyperloglog_uv:" + dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        jedis.pfmerge("hyperloglog_uv:" + startDateStr + ":" + endDateStr, dateList.toArray(new String[dateList.size()]));

        return jedis.pfcount("hyperloglog_uv:" + startDateStr + ":" + endDateStr);
    }

    public static void main(String[] args) {
        HyperLogLogUVDemo demo = new HyperLogLogUVDemo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date startDate = new Date();
        calendar.setTime(startDate);
        //初始化7天的UV数据
        for (int i = 0; i < 7; i++) {
            demo.initUVData(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date endDate = calendar.getTime();
        long dateUV = demo.getUV(startDate, endDate);
        System.out.println("日期期间" + dateFormat.format(startDate) + "-" + dateFormat.format(endDate) + "的UV为" + dateUV);
    }
}
