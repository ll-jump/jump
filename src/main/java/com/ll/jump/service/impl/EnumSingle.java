package com.ll.jump.service.impl;

import com.ll.jump.model.User;


public enum EnumSingle {
    INSTANCE;
    private User user;

    public EnumSingle getInstance() {
        return INSTANCE;
    }


    EnumSingle() {
        user = new User();
    }

    public User getUser() {
        return user;
    }
}
