package com.ll.jump.base.mask.handler;

import com.ll.jump.base.mask.DataMaskHandler;
import com.ll.jump.base.mask.DataMaskHandlerRegistration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈数据脱敏处理器抽象类〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
public abstract class AbstractDataMaskHandler implements DataMaskHandler, InitializingBean {
    static final String MASK_CHAR = "*";

    @Autowired
    private DataMaskHandlerRegistration dataMaskHandlerRegistration;

    @Override
    public void afterPropertiesSet()
            throws Exception {
        this.dataMaskHandlerRegistration.register(this);
    }
}