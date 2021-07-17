package com.ll.jump.service.impl.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiLin
 * @desc 锁条件测试
 * @create 2021-06-09 14:58
 **/
public class LockConditionTest {
    /**
     * 可重入锁
     */
    private final ReentrantLock lock;
    /**
     * 唤醒条件
     */
    private final Condition condition;

    public LockConditionTest() {
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    public void test(){
        Object o = new Object();
        final ReentrantLock putLock = this.lock;
        Thread a = new Thread(() -> {
//            synchronized (o){
                System.out.println("a线程执行开始");
                putLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    putLock.unlock();
                }
                System.out.println("a线程执行结束");
//            }
        });

        Thread b = new Thread(() -> {
            synchronized (o){
                System.out.println("b线程执行开始");
                putLock.lock();
                try {
                    this.condition.notify();
                }finally {
                    putLock.unlock();
                }
                System.out.println("b线程执行结束");
            }
        });

        a.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();
    }

    public static void main(String[] args) {
        LockConditionTest test = new LockConditionTest();
        test.test();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
