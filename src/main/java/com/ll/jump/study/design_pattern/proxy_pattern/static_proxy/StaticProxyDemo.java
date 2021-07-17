package com.ll.jump.study.design_pattern.proxy_pattern.static_proxy;

import com.ll.jump.study.design_pattern.proxy_pattern.UserServiceImpl;

/**
 * 〈静态代理demo〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        UserServiceProxy userServiceProxy = new UserServiceProxy(new UserServiceImpl());
        userServiceProxy.addUser();
    }
}