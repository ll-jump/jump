package com.ll.jump.study.design_pattern.cor_pattern;

/**
 * 〈记录日志抽象类〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public  abstract class AbstractLog {
    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int ERROR = 3;

    protected int level;
    private AbstractLog nextLog;
    public void setNextLog(AbstractLog log){
        this.nextLog = log;
    }

    public void log(int level, String message){
        if (this.level != level){
            nextLog.log(level, message);
        }else {
            message(message);
        }
    }

    public abstract void message(String message);
}