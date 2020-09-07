package com.ll.jump.service.impl;

/**
 * 〈放入任务线程测试〉
 *
 * @author LiLin
 * @date 2020/7/9 0009
 */
public class TestPutTask implements Runnable {
    private String type;
    public TestPutTask(String type){
        this.type = type;
    }
    @Override
    public void run() {
        int i = 0;
        while (true){
            TestTakeTask.putQueue( type + String.valueOf(i++));
            System.out.println("put:" + type + String.valueOf(i++));
        }
    }
}