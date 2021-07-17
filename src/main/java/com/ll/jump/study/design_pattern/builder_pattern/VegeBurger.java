package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈素汉堡〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class VegeBurger extends AbstractBurgerFood {
    @Override
    public String name() {
        return "vege burger";
    }

    @Override
    public float price() {
        return 16;
    }
}