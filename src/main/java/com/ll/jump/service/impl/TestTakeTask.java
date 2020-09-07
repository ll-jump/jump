package com.ll.jump.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 〈取出任务线程测试〉
 *
 * @author LiLin
 * @date 2020/7/9 0009
 */
public class TestTakeTask implements Runnable{
    static LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>(5);
    public static void putQueue(String s){
        try {
            //put 队列满后，会wait，等到队列有空间后，放入
            lbq.put(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void offerQueue(String s){
        //put 队列满后，会返回false
        lbq.offer(s);
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(10000);
                String s = lbq.take();
                System.out.println("take:" + s);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}