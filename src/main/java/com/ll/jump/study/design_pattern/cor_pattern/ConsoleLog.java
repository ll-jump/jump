package com.ll.jump.study.design_pattern.cor_pattern;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public class ConsoleLog {
    public void log(int level, String message){
        ErrorLog errorLog = new ErrorLog();
        InfoLog infoLog = new InfoLog();
        DebugLog debugLog = new DebugLog();
        errorLog.setNextLog(infoLog);
        infoLog.setNextLog(debugLog);
        errorLog.log(level, message);
    }
}