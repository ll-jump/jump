package com.ll.jump.study.design_pattern.cor_pattern;

/**
 * 〈责任链demo〉
 *
 * @author LiLin
 * @date 2020/8/2 0002
 */
public class CorDemo {
    public static void main(String[] args) {
        ConsoleLog consoleLog = new ConsoleLog();
        consoleLog.log(1, "aaaaa");
        consoleLog.log(2, "bbbbb");
        consoleLog.log(3, "ccccc");
    }
}