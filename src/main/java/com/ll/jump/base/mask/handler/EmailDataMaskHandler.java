package com.ll.jump.base.mask.handler;

import com.ll.jump.base.mask.MaskTypeEnums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 〈邮箱脱敏处理器〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class EmailDataMaskHandler extends AbstractDataMaskHandler {
    public String mask(String source) {
        if (StringUtils.isNotBlank(source)) {
            int start = StringUtils.indexOf(source, "@");
            if (start >= 0) {
                start += 1;
                int end = source.length();
                int repeat = end - start;
                return StringUtils.overlay(source, StringUtils.repeat("*", repeat), start, end);
            }
            return source;
        }
        return null;
    }

    @Override
    public String getType() {
        return MaskTypeEnums.EMAIL.name();
    }
}