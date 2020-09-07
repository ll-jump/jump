package com.ll.jump.design_pattern.flyweight_pattern;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class Circle implements Shap {
    private String color;
    public Circle(String color){
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("shap circle " + color);
    }
}