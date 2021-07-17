package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈橙汁〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class OrangeDrink extends AbstractDrinkFood {
    @Override
    public String name() {
        return "orange drink";
    }

    @Override
    public float price() {
        return 6;
    }
}