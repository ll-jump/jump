package com.ll.jump.service.impl.test;

import com.ll.jump.JumpApplication;
import com.ll.jump.base.mask.DataMask;
import com.ll.jump.model.User;
import com.ll.jump.service.AsyncService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/1/15 0015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JumpApplication.class)
public class LLTestBeanInit {

    @Autowired
    AsyncService asyncService;
    @Autowired
    DataMask dataMask;
    @Test
    public void testMask(){
        User user = new User();
        user.setName("张三");
        dataMask.mask(user);
        System.out.println(user.toString());


    }

    @Test
    public void testLock(){

        for (int i = 0; i < 3; i++) {
            asyncService.test3(i,"a" + i);
        }
    }
}