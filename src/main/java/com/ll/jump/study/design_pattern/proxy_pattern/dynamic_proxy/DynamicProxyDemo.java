package com.ll.jump.study.design_pattern.proxy_pattern.dynamic_proxy;

import com.ll.jump.study.design_pattern.proxy_pattern.UserService;
import com.ll.jump.study.design_pattern.proxy_pattern.UserServiceImpl;

/**
 * 〈动态代理demo〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        UserService userService = (UserService)dynamicProxy.bind(new UserServiceImpl());
        userService.addUser();
    }
}