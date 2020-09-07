package com.ll.jump.service.impl;

import com.ll.jump.service.ActivityService;

import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/8 0008
 */
@Component("CASH_PLEDGE")
public class ActivityCashPledgeServiceImpl implements ActivityService {
    @Override
    public void start(String name) {
        System.out.println("CASH_PLEDGE start.");
    }

    @Override
    public void end(String name) {
        System.out.println("CASH_PLEDGE end.");
    }
}