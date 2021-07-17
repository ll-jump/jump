package com.ll.jump.study.design_pattern.cor_pattern;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public class DebugLog extends AbstractLog {
    public DebugLog(){
        this.level = 1;
    }
    @Override
    public void message(String message) {
        System.out.println("debug log:" + message);
    }
}