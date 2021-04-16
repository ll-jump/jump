package com.ll.jump.service.impl.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiLin
 * @desc
 * @create 2021-02-23 14:03
 **/
public class JvmTest {
    public static void main(String[] args) throws InterruptedException {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
//        Thread.sleep(30000);
//        while (true) {
//            load();
//        }

//        byte[] array1 = new byte[1024*1024];
//        array1 = new byte[1024*1024];
//        array1 = new byte[1024*1024];
//        array1 = null;
//        byte[] array2 = new byte[2*1024*1024];
//        byte[] array3 = new byte[2*1024*1024];

//        while (true){
//            load();
//        }
    }

    static class Data {
    }

    public static void load() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000);
    }
}
