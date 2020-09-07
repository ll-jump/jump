package com.ll.jump.exception;

public enum ServiceErrorCode {
    /**
     * 前三位为系统标识，后三位为错误标识
     */
    E008001("008001", "用户未登录"),
    E008002("008002", "用户登录失效"),


    E008997("008997", "系统繁忙，请稍后重试！"),
    E008998("008998", "系统接口参数错误"),
    E008999("008999", "系统繁忙，请稍后重试！");



    private String code;

    private String desc;

    ServiceErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
