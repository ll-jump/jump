package com.ll.jump.base.mask.handler;

import com.ll.jump.base.mask.MaskTypeEnums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 〈身份证脱敏处理器〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class IdCardDataMaskHandler extends AbstractDataMaskHandler {
    private static final int START_POSITION = 4;
    private static final int END_POSITION = 4;

    public String mask(String source) {
        if (StringUtils.isNotBlank(source)) {
            int start = START_POSITION;
            int end = source.length() - END_POSITION;
            int repeat = end - start;

            return StringUtils.overlay(source, StringUtils.repeat(MASK_CHAR, repeat), start, end);
        }
        return null;
    }

    @Override
    public String getType() {
        return MaskTypeEnums.ID_CARD.name();
    }
}