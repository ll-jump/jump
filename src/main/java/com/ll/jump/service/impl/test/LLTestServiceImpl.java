package com.ll.jump.service.impl.test;

import com.ll.jump.service.AsyncService;
import com.ll.jump.service.TestService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 〈〉
 *
 * @author LiLin
 * @create 2019/7/24 0024
 */
public class LLTestServiceImpl implements TestService {
    @Autowired
    AsyncService asyncService;

    /**
     * 最长不重复字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for(int i = 0; i < s.length(); ++i){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    @Test
    public void test(){
        Long a = 10L;
        System.out.println(String.format("%010d", a));
//        long t1 = System.currentTimeMillis();
//        for (int i = 0; i < 1; ++i){
//            Date date = new Date();
//            DateTime dateTime = new DateTime();
//            DateTime dateTime1 = dateTime.plusMonths(1);
//            System.out.println(dateTime1.toString("yyyy-MM-dd hh:mm:ss"));
////            String s = DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
//        }
//        System.out.println("DateFormatUtils use time:" + (System.currentTimeMillis() - t1));
    }
    @Test
    public void test2(){
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i){
//            Date date = new Date();
            asyncService.test(i);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String s = format.format(date);
        }
        System.out.println("SimpleDateFormat use time:" + (System.currentTimeMillis() - t1));
    }

    @Test
    public void testQueue() throws InterruptedException {
        LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(10);
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
        int i = 0;
        while (true){

            System.out.println(i++);
            //take方法队列为空，会阻塞等待队列有数据
            System.out.println(lbq.take());
            //poll方法队列为空，直接返回null
            //System.out.println(lbq.poll());
//            abq.put(String.valueOf(i));

        }
    }
}
