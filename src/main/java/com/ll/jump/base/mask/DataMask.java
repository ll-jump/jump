package com.ll.jump.base.mask;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

/**
 * 〈对象脱敏〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Component
public class DataMask {
    @Autowired
    private DataMaskHandlerRegistration dataMaskHandlerRegistration;

    /**
     * 根据类型返回处理器
     *
     * @return
     */
    public DataMaskHandler getHandler(String type) {
        return dataMaskHandlerRegistration.getHandler(type);
    }

    /**
     * 脱敏
     *
     * @param object
     */
    public void mask(Object object) {
        if (object != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            Stream.of(fields).forEach(field -> {
                if (field != null) {
                    Mask annotation = field.getAnnotation(Mask.class);
                    if (annotation != null) {
                        String type = StringUtils.upperCase(annotation.type());
                        if ("ENTITY".equals(type)) {
                            maskEntity(object, field);
                        } else if ("LIST".equals(type)) {
                            maskList(object, field);
                        } else {
                            mask(object, field, type);
                        }
                    }

                }
            });
        }
    }

    /**
     *  脱敏字段属性为List
     * @param object
     * @param field
     */
    @SuppressWarnings("unchecked")
    private void maskList(Object object, Field field) {
        field.setAccessible(true);
        try {
            List<Object> chirldObjects = (List<Object>)field.get(object);
            chirldObjects.forEach(chirldObject -> mask(chirldObject));
            field.set(object, chirldObjects);

        } catch (IllegalAccessException ex) {
        }

    }

    /**
     * 脱敏字段属性为自定义对象
     * @param object
     * @param field
     */
    private void maskEntity(Object object, Field field) {
        field.setAccessible(true);
        try {
            Object chirldObject = field.get(object);
            mask(chirldObject);
            field.set(object, chirldObject);
        } catch (IllegalAccessException ex) {
        }

    }

    /**
     * 脱敏字段属性为基本类型或String
     *
     * @param object
     * @param field
     */
    private void mask(Object object, Field field, String type) {
        DataMaskHandler handler = getHandler(type);
        if (handler != null) {
            field.setAccessible(true);
            try {
                String maskValue = handler.mask(String.valueOf(field.get(object)));
                field.set(object, maskValue);
            } catch (IllegalAccessException ex) {
            }
        }
    }
}