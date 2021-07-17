package com.ll.jump.study.design_pattern.flyweight_pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈图形工厂类〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class ShapFactory {
    private Map<String, Shap> shapMap = new HashMap<>();
    public Circle getCircle(String color){
        if (shapMap.get(color) == null){
            Circle circle = new Circle(color);
            shapMap.put(color, circle);
            return circle;
        }else {
            return (Circle)shapMap.get(color);
        }
    }
}