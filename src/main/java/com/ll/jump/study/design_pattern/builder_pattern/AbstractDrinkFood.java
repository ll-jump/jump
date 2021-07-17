package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈饮料〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public abstract class AbstractDrinkFood implements Food{
    @Override
    public Packing pack(){
        return new GlassPacking();
    }
}
