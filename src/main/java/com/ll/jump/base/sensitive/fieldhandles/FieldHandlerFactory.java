package com.ll.jump.base.sensitive.fieldhandles;

import com.ll.jump.base.sensitive.enums.SensitiveFieldTypeEnum;

/**
 * 〈脱敏实现工厂类〉
 *
 * @author LiLin
 * @date 2020/5/9 0009
 */
public class FieldHandlerFactory {
    private static final FieldHandler GENERAL_FIELD_HANDLER = new GeneralFieldHandler();
    private static final FieldHandler JSON_FIELD_HANDLER = new JsonFieldHandler();

    public static FieldHandler getFieldHandler(SensitiveFieldTypeEnum fieldTypeEnum) {
        switch(fieldTypeEnum) {
            case GENERAL:
                return GENERAL_FIELD_HANDLER;
            case JSON:
                return JSON_FIELD_HANDLER;
            case NONE:
                return null;
            default:
                return null;
        }
    }
}