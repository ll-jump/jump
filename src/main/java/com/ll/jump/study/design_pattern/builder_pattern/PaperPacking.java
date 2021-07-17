package com.ll.jump.study.design_pattern.builder_pattern;

/**
 * 〈纸包装〉
 *
 * @author LiLin
 * @date 2020/7/26 0026
 */
public class PaperPacking implements Packing{

    @Override
    public String pack() {
        return "paper pack";
    }
}