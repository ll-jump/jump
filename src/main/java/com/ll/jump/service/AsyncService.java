package com.ll.jump.service;

import org.springframework.stereotype.Service;

/**
 * 〈异步〉
 *
 * @author LiLin
 * @create 2019/8/2 0002
 */
public interface AsyncService{
    void test(int i);
    void test2(int i);
    void test3(int sortNum, String s);
    void test4(String s);
}
