package com.ll.jump.design_pattern.cor_pattern;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public class InfoLog extends AbstractLog {
    public InfoLog(){
        this.level = 2;
    }
    @Override
    public void message(String message) {
        System.out.println("info log:" + message);
    }
}