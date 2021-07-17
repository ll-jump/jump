package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈可乐〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class ColaDrink extends AbstractDrinkFood {
    @Override
    public String name() {
        return "cola drink";
    }

    @Override
    public float price() {
        return 7;
    }
}