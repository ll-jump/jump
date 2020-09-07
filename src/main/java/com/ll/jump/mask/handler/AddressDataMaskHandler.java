package com.ll.jump.mask.handler;

import com.ll.jump.mask.MaskTypeEnums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 〈地址脱敏处理器〉
 *
 * @author LiLin
 * @date 2020/1/17 0017
 */
@Service
public class AddressDataMaskHandler extends AbstractDataMaskHandler {
    public String mask(String source) {
        if (StringUtils.isNotBlank(source)) {
            int start = StringUtils.indexOf(source, "区");
            if (start < 0) {
                start = StringUtils.indexOf(source, "市");
            }
            if (start < 0) {
                start = StringUtils.indexOf(source, "省");
            }
            start++;
            int end = source.length();
            int repeat = end - start;
            return StringUtils.overlay(source, StringUtils.repeat("*", repeat), start, end);
        }
        return null;
    }

    @Override
    public String getType() {
        return MaskTypeEnums.ADDRESS.name();
    }
}