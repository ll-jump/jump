package com.ll.jump.repository;

import com.ll.jump.mapper.UserMapper;
import com.ll.jump.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈用户〉
 *
 * @author LiLin
 * @create 2019/7/12 0012
 */
@Service
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }

    public User select(User user) {
        return userMapper.select(user);
    }
}
