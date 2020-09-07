package com.ll.jump.design_pattern.cor_pattern;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public class ErrorLog extends AbstractLog {
    public ErrorLog(){
        this.level = 3;
    }
    @Override
    public void message(String message) {
        System.out.println("error log:"+message);
    }
}