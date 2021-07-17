package com.ll.jump.study.design_pattern.decorator_pattern;

/**
 * 〈装饰模式demo〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        Circle circle = new Circle();
        RedShap redCircleShap = new RedShap(circle);
        redCircleShap.draw();
        System.out.println();
        Square square = new Square();
        RedShap redSquareShap = new RedShap(square);
        redSquareShap.draw();
    }
}