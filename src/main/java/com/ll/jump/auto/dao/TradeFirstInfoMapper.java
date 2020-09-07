package com.ll.jump.auto.dao;

import com.ll.jump.auto.entity.TradeFirstInfo;

public interface TradeFirstInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TradeFirstInfo record);

    int insertSelective(TradeFirstInfo record);

    TradeFirstInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TradeFirstInfo record);

    int updateByPrimaryKey(TradeFirstInfo record);
}