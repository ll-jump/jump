package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈套餐构建〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class MealBuilder {
    public Meal getMealOne(){
        Meal meal = new Meal();
        ChickenBurger chickenBurger = new ChickenBurger();
        meal.add(chickenBurger);
        ColaDrink colaDrink = new ColaDrink();
        meal.add(colaDrink);
        meal.detail();
        meal.price();
        return meal;
    }

    public Meal getMealTwo(){
        Meal meal = new Meal();
        VegeBurger vegeBurger = new VegeBurger();
        meal.add(vegeBurger);
        OrangeDrink orangeDrink = new OrangeDrink();
        meal.add(orangeDrink);
        meal.detail();
        meal.price();
        return meal;
    }
}