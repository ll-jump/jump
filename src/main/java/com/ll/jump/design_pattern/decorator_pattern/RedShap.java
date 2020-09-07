package com.ll.jump.design_pattern.decorator_pattern;

/**
 * 〈红色图形〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class RedShap extends ShapDecorator {
    public RedShap(Shap shap){
        super(shap);
    }
    @Override
    public void draw() {
        shap.draw();
        red();
    }

    private void red(){
        System.out.println("color red");
    }
}