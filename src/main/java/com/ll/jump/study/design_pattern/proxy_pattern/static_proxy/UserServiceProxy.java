package com.ll.jump.study.design_pattern.proxy_pattern.static_proxy;

import com.ll.jump.study.design_pattern.proxy_pattern.UserService;

/**
 * 〈用户接口代理类〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class UserServiceProxy implements UserService {
    private UserService userService;
    public UserServiceProxy(UserService userService){
        this.userService = userService;
    }

    @Override
    public void addUser(){
        userService.addUser();
        System.out.println("打印一条日志");
    }
}