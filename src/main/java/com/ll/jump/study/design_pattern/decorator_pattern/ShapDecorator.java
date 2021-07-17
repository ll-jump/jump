package com.ll.jump.study.design_pattern.decorator_pattern;

/**
 * 〈shap装饰抽象类〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public abstract class ShapDecorator implements Shap {
    protected Shap shap;
    public ShapDecorator(Shap shap){
        this.shap = shap;
    }
}