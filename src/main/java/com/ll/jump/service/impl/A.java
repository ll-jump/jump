package com.ll.jump.service.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/1/15 0015
 */
public class A {
    /**
     * 使用公平锁，防止一个线程连续获取锁的情况
     */
    private Lock lock = new ReentrantLock(true);
    // 计数
    private int COUNT = 0;
    // 循环次数
    private int LOOP_NUM = 10;
    // 计数取模
    private int MOD = 3;

    private void printChar(int threadIdentify, String s) {
//        for (int i = 0; i < LOOP_NUM; ) {
//            lock.lock();
//            try {
//                /**
//                 * threadIdentify: 0 表示线程 a, 1 表示线程 b, 2 表示线程 c
//                 */
//                if (COUNT % MOD == threadIdentify) {
//                    System.out.println( s);
//                    COUNT++;
//                    i++;
//                }
//            } finally {
//                lock.unlock();
//            }
//        }

        for (int i = 0; i < 1; ) {
            lock.lock();
            System.out.println(String.format("aaaaa:%s,%s,%s,%s", threadIdentify, s, i,COUNT));
            try {
                /**
                 * threadIdentify: 0 表示线程 a, 1 表示线程 b, 2 表示线程 c
                 */
                if (COUNT % MOD == threadIdentify) {
                    System.out.println( s);
                    COUNT++;
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        A lockDemo = new A();
        Thread a = new Thread(() -> lockDemo.printChar(1,"A"), "AA");
        Thread b = new Thread(() -> lockDemo.printChar(0, "B"), "BB");
        Thread c = new Thread(() -> lockDemo.printChar(2, "C"), "CC");

        Thread d = new Thread(() -> lockDemo.printChar(1,""));
        a.start();
        b.start();
        c.start();
    }
}