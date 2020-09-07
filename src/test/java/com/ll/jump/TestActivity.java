package com.ll.jump;

import com.ll.jump.enums.ActivityEnum;
import com.ll.jump.factory.ActivityFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/2 0002
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JumpApplication.class)
public class TestActivity {
    @Autowired
    private ActivityFactory activityFactory;
    @Test
    public void testA(){
        System.out.println(ActivityEnum.BRUSH.name() + ":" + ActivityEnum.BRUSH.getMessage());
        activityFactory.getActivityService(ActivityEnum.BRUSH.name()).start(ActivityEnum.BRUSH.name());
        activityFactory.getActivityService(ActivityEnum.CASH_PLEDGE.name()).start(ActivityEnum.CASH_PLEDGE.name());
    }

}