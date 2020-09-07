package com.ll.jump.mask.handler;

import com.ll.jump.mask.MaskTypeEnums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 〈手机号脱敏处理器〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class TelephoneDataMaskHandler extends AbstractDataMaskHandler {
    private static final int START_POSITION = 3;
    private static final int END_POSITION = 4;

    public String mask(String source) {
        if (StringUtils.isNotBlank(source)) {
            int start = 3;
            int end = source.length() - 4;
            int repeat = end - start;

            return StringUtils.overlay(source, StringUtils.repeat("*", repeat), start, end);
        }
        return null;
    }

    @Override
    public String getType() {
        return MaskTypeEnums.TELEPHONE.name();
    }
}