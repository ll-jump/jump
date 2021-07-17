package com.ll.jump.base.mask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈数据脱敏处理器注册类〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class DataMaskHandlerRegistration implements InitializingBean {
    private Map<String, DataMaskHandler> handlers;

    @Override
    public void afterPropertiesSet()
            throws Exception {
        this.handlers = new HashMap();
    }

    public void register(DataMaskHandler handler) {
        if (handler != null) {
            this.handlers.put(handler.getType(), handler);
        }
    }

    public DataMaskHandler getHandler(String type) {
        return (DataMaskHandler) this.handlers.get(type);
    }
}