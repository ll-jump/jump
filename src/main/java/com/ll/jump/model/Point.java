package com.ll.jump.model;

/**
 * 〈点〉
 *
 * @author LiLin
 * @date 2020/7/21 0021
 */
public class Point {
    private Double x;
    private Double y;
    public Point (Double x , Double y) {
        this.x = x;
        this.y = y;
    }
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
}