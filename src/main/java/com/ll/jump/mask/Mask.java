package com.ll.jump.mask;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈注解〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Mask {
    public abstract String type();
}
