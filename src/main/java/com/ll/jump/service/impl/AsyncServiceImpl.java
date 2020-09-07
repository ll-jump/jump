package com.ll.jump.service.impl;

import com.ll.jump.service.AsyncService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈〉
 *
 * @author LiLin
 * @create 2019/8/2 0002
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    private static String lock = "lock";
    private Lock lockR = new ReentrantLock(true);
    private int COUNT = 0;
    private int MOD = 3;
    @Async
    @Override
    public void test(int i) {
        Date date = new Date();
        String s = DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
        System.out.println(s);
    }

    @Async
    @Override
    public void test2(int i) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = sdf.format(date);
        System.out.println(s);
    }

    @Async
    @Override
    public void test3(int sortNum, String s) {
        for (int i = 0; i < 1;) {
            lockR.lock();
            try{
                if (COUNT % MOD  == sortNum){
                    System.out.println(s);
                    COUNT ++;
                    i ++;
                }
            }finally {
                lockR.unlock();
            }
        }
    }

    @Async
    @Override
    public void test4(String s) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
