package com.ll.jump;

import com.ll.jump.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JumpApplicationTests {
    @MockBean
    MockBeanTestService mockBeanTestService;
    @Test
    public void contextLoads() {
        User user = new User();
        user.setId("1");
        user.setName("b");
        Mockito.when(mockBeanTestService.test(user)).thenReturn("abc");
        User user2 = new User();
        user2.setId("1");
        user2.setName("b");
        System.out.println(mockBeanTestService.test(user2));
    }

    @Test
    public void testCron(){
        Date date  = new Date();
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }

        System.out.println(formatTimeStr);
    }
}
