package com.ll.jump.design_pattern.builder_pattern;

/**
 * 〈食物〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public interface Food {
    String name();
    float price();
    Packing pack();
}
