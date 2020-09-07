package com.ll.jump.design_pattern.builder_pattern;

/**
 * 〈汉堡〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public abstract class AbstractBurgerFood implements Food{
    @Override
    public Packing pack(){
        return new PaperPacking();
    }
}