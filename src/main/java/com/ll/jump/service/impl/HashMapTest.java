package com.ll.jump.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/8/9 0009
 */
public class HashMapTest {
    public static void main(String[] args)
        throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String a = "a";
        System.out.println(Integer.toBinaryString(a.hashCode()));
//        Map<String, String> map = new HashMap<String, String>();
//                for (int i = 0; i < 13; i++) {
//            map.put(String.valueOf(i), String.valueOf(i));
//        }
//        Class<?> mapType = map.getClass();
//        Method capacity = mapType.getDeclaredMethod("capacity");
//        capacity.setAccessible(true);
//        System.out.println("capacity : " + capacity.invoke(map));
//
//        Field size = mapType.getDeclaredField("size");
//        size.setAccessible(true);
//        System.out.println("size : " + size.get(map));


//        int tatal = 50;
//        Long start;
//        start = System.currentTimeMillis();
//        Map<String, Integer> mapT = new TreeMap<String, Integer>(
//            (o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2)));
//
//        for (int i = 0; i < tatal; i++) {
//            mapT.put(String.valueOf(i), i);
//        }
//
//        mapT.forEach((k, v) -> {
//            System.out.println(k + " " + v);
//        });

//        start = System.currentTimeMillis();
//        Map<String, Integer> mapL = new LinkedHashMap<>();
//        for (int i = 0; i < tatal; i++) {
//            mapL.put(String.valueOf(i), i);
//        }
//
//        mapL.forEach((k, v) -> {
//            System.out.println(k + " " + v);
//        });
//
//        System.out.println("LinkedHashMap put cost:" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        mapL.forEach((k, v) -> {
//            if (v % tatal == 0) {
//                System.out.println(k + " " + v);
//            }
//        });
//        System.out.println("LinkedHashMap foreach cost:" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        for (int i = 0; i < tatal; i++) {
//            Integer temp = mapL.get(String.valueOf(i));
//            if (temp % tatal == 0) {
//                System.out.println(temp);
//            }
//        }
//        System.out.println("LinkedHashMap get cost:" + (System.currentTimeMillis() - start));
//        System.out.println();
//
//        Map<String, Integer> map = new HashMap<>();
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i < tatal; i++) {
//            map.put(String.valueOf(i), i);
//        }
//        System.out.println("HashMap put cost:" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        map.forEach((k, v) -> {
//            if (v % tatal == 0) {
//                System.out.println(k + " " + v);
//            }
//        });
//        System.out.println("HashMap foreach cost:" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        for (int i = 0; i < tatal; i++) {
//            Integer temp = map.get(String.valueOf(i));
//            if (temp % tatal == 0) {
//                System.out.println(temp);
//            }
//        }
//        System.out.println("HashMap get cost:" + (System.currentTimeMillis() - start));
        //        System.out.println();
        //        start = System.currentTimeMillis();
        //        Map<String,Integer> map1 = new ConcurrentHashMap<>();
        //        for (int i = 0; i < tatal; i++) {
        //            map1.put(String.valueOf(i), i);
        //        }
        //        System.out.println("ConcurrentHashMap put cost:" + (System.currentTimeMillis() - start));
        //        map1.forEach((k,v)->{
        //            if (v % tatal == 0){
        //                System.out.println(k + " " + v );
        //            }
        //        });
        //        System.out.println("ConcurrentHashMap foreach cost:" + (System.currentTimeMillis() - start));
        //
        //        start = System.currentTimeMillis();
        //        for (int i = 0; i < tatal; i++) {
        //            Integer temp = map.get(String.valueOf(i));
        //            if (temp % tatal == 0){
        //                System.out.println(temp);
        //            }
        //        }
        //        System.out.println("ConcurrentHashMap get cost:" + (System.currentTimeMillis() - start));
    }
}