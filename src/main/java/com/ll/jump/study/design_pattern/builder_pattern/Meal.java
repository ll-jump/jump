package com.ll.jump.study.design_pattern.builder_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈套餐〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class Meal {
    private List<Food> foodList = new ArrayList<>();

    public void add(Food food){
        foodList.add(food);
    }

    public void price(){
        float price = 0;
        for (Food food : foodList) {
            price += food.price();
        }
        System.out.println("price:" + price);
    }

    public void detail(){
        for (Food food : foodList) {
            System.out.println(food.name() + " " + food.pack().pack()  + " " + food.price());
        }
    }
}