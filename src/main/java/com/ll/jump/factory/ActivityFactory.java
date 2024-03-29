package com.ll.jump.factory;

import com.ll.jump.base.exception.ServiceErrorCode;
import com.ll.jump.base.exception.ServiceException;
import com.ll.jump.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈活动工厂类〉
 *
 * @author LiLin
 * @date 2020/4/8 0008
 */
@Service
public class ActivityFactory {
    @Autowired
    private Map<String, ActivityService> activityServiceMap;

//    private final Map<String, ActivityService> activityServiceMap = new ConcurrentHashMap<>();
//    @Autowired
//    public ActivityFactory(Map<String, ActivityService> activityServiceMap){
//        this.activityServiceMap.clear();
//        activityServiceMap.forEach((k,v)->{
//            this.activityServiceMap.put(k,v);
//        });
//    }

    public ActivityService getActivityService(String activityType){
        ActivityService activityService = activityServiceMap.get(activityType);
        if (activityService == null){
            throw new ServiceException(ServiceErrorCode.E008999);
        }else {
            return activityService;
        }
    }
}