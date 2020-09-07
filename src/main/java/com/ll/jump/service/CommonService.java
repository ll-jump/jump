package com.ll.jump.service;

import com.google.gson.Gson;

import com.ll.jump.exception.ServiceException;
import com.ll.jump.result.ServiceResult;

/**
 * 公共接口
 */
public interface CommonService {
    Gson gson = new Gson();

    /**
     * 处理服务返回结果
     */
    default <T> T dealServicResult(ServiceResult serviceResult, Class<T> c) {
        boolean isSuccess = serviceResult.isSuccess();
        if (isSuccess) {
            T t = gson.fromJson(gson.toJson(serviceResult.getData()), c);
            return t;
        } else {
            throw new ServiceException(serviceResult.getCode(), serviceResult.getMessage());
        }
    }
}
