package com.ll.jump.mapper;

import com.ll.jump.model.User;

import org.apache.ibatis.annotations.Mapper;


/**
 * 〈用户mapper〉
 *
 * @author LiLin
 * @create 2019/7/12 0012
 */
@Mapper
public interface UserMapper {
    void insert(User user);
    User select(User user);
}
