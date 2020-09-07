package com.ll.jump.mask;

import org.apache.commons.lang3.StringUtils;

/**
 * 脱敏类型
 */
public enum MaskTypeEnums {
    ID_CARD("ID_CARD", "身份证号"),

    BANK_CARD("BANK_CARD", "银行卡号"),

    NAME("NAME", "姓名"),

    TELEPHONE("TELEPHONE", "电话号码"),

    ADDRESS("ADDRESS", "地址"),

    EMAIL("EMAIL", "电子邮件");

    private String code;
    private String text;

    private MaskTypeEnums(String code, String text)
    {
        this.code = code;
        this.text = text;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getText()
    {
        return this.text;
    }

    public static MaskTypeEnums getEnums(String code)
    {
        for (MaskTypeEnums enums : values()) {
            if (StringUtils.equalsIgnoreCase(code, enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
