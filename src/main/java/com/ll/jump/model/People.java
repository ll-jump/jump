package com.ll.jump.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/23 0023
 */
public class People implements Serializable {
    private static final long serialVersionUID = 1130688869795234670L;
    private String name;
    private transient Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
//    @Override
//    public String toString(){
//        return ReflectionToStringBuilder.toString("name="+name + ",age="+age);
////        return ReflectionToStringBuilder.toString(this);
//    }
}