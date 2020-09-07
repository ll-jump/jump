package com.ll.jump.design_pattern.builder_pattern;

/**
 * 〈鸡肉汉堡〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class ChickenBurger extends AbstractBurgerFood {
    @Override
    public String name() {
        return "chicken burger";
    }

    @Override
    public float price() {
        return 18;
    }
}