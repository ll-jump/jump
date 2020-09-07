package com.ll.jump.design_pattern;

import com.ll.jump.design_pattern.builder_pattern.MealBuilder;
import org.junit.Test;

/**
 * 〈设计模式测试类〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class DesignPatternTest {
    @Test
    public void testBuilderPattern(){
        MealBuilder mealBuilder = new MealBuilder();
        mealBuilder.getMealOne();
    }
}