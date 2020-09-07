package com.ll.jump.service;

import com.ll.jump.model.User;

public interface UserService {
    void insert(User user);
    User select(User user);
}
