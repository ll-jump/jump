package com.ll.jump.study.design_pattern.flyweight_pattern;

/**
 * 〈享元模式demo〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        ShapFactory shapFactory = new ShapFactory();
        shapFactory.getCircle("red").draw();
        shapFactory.getCircle("blue").draw();
        shapFactory.getCircle("blue").draw();
        shapFactory.getCircle("red").draw();
    }
}