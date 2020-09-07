package com.ll.jump.mask.handler;

import com.ll.jump.mask.MaskTypeEnums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 〈姓名脱敏处理器〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class NameDataMaskHandler extends AbstractDataMaskHandler {
    private static final int START_POSITION = 1;
    @Override
    public String mask(String source)
    {
        if (StringUtils.isNotBlank(source))
        {
            if (StringUtils.isNotBlank(source)) {
                int start = START_POSITION;
                int end = source.length();
                int repeat = end - start;

                return StringUtils.overlay(source, StringUtils.repeat(MASK_CHAR, repeat), start, end);
            }
        }
        return null;
    }
    @Override
    public String getType()
    {
        return MaskTypeEnums.NAME.name();
    }
}