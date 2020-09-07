package com.ll.jump.enums;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/1 0001
 */
public enum  ActivityEnum {
    BRUSH("刷购返"),CASH_PLEDGE("押金");

    private String message;
    ActivityEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}