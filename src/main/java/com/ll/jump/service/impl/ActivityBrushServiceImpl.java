package com.ll.jump.service.impl;

import com.ll.jump.service.ActivityService;

import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/8 0008
 */
@Component("BRUSH")
public class ActivityBrushServiceImpl implements ActivityService {
    @Override
    public void start(String name) {
        System.out.println("BRUSH start." + name);
    }

    @Override
    public void end(String name) {
        System.out.println("BRUSH end." + name);
    }
}