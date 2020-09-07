package com.ll.jump.service.impl;

import com.ll.jump.model.User;
import com.ll.jump.repository.UserRepository;
import com.ll.jump.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈〉
 *
 * @author LiLin
 * @create 2019/7/12 0012
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public void insert(User user) {
        logger.info("insert start.");
        userRepository.insert(user);
    }

    @Override
    public User select(User user) {
        logger.info("select start.");
        return userRepository.select(user);
    }
}
