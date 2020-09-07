package com.ll.jump.design_pattern.decorator_pattern;

/**
 * 〈圆形〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class Circle implements Shap {
    @Override
    public void draw() {
        System.out.println("shap circle");
    }
}