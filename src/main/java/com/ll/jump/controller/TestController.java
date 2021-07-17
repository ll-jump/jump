package com.ll.jump.controller;

import com.ll.jump.model.User;
import com.ll.jump.base.result.ServiceResult;
import com.ll.jump.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈测试〉
 *
 * @author LiLin
 * @create 2019/7/10 0010
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/html/index.html");
        return modelAndView;
    }

    @RequestMapping(value = "user/insert", method = RequestMethod.POST)
    public ServiceResult insertUser(@RequestBody User user) {
        userService.insert(user);
        return ServiceResult.success(null);
    }

    @RequestMapping(value = "user/select", method = RequestMethod.POST)
    public ServiceResult<User> selectUser(@RequestBody User user) {
        User userResult = userService.select(user);
        return ServiceResult.success(userResult);
    }
}
