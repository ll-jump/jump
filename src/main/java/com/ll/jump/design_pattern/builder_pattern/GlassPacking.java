package com.ll.jump.design_pattern.builder_pattern;

/**
 * 〈玻璃包装〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class GlassPacking implements Packing {
    @Override
    public String pack() {
        return "glass pack";
    }
}