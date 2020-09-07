package com.ll.jump.mask;

/**
 * 〈数据脱敏处理器接口〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
public abstract interface DataMaskHandler {
    public abstract String mask(String paramString);

    public abstract String getType();
}
