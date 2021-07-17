package com.ll.jump.model;

import com.ll.jump.base.mask.Mask;

import java.io.Serializable;
import java.util.Objects;

/**
 * 〈用户对象〉
 *
 * @author LiLin
 * @create 2019/7/12 0012
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6910611766428801198L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name);
    }



//    @Override
//    public String toString() {
//        return "User{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    private String id;
    @Mask(type = "NAME")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
