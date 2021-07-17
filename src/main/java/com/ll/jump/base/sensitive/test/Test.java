package com.ll.jump.base.sensitive.test;

import com.google.common.collect.ImmutableMap;

import com.ll.jump.base.sensitive.enums.SensitiveRulesEnum;
import com.ll.jump.base.sensitive.utils.SensitiveProcessUtils;

import java.util.Map;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/5/9 0009
 */
public class Test {
    public static void main(String[] args) {
        //对对象脱敏
        UserDTO userDTO = new UserDTO();
        userDTO.setName("张三");
        userDTO.setType("123");
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setEmail("1234567@qq.com");
        userDetailDTO.setCardNo("1234567890");
        userDetailDTO.setIdCardNo("372325199901021234");
        userDTO.setUserDetailDTO(userDetailDTO);
        System.out.println(userDTO.toString());

        //对单个字符串脱敏
        System.out.println(SensitiveProcessUtils.shield(SensitiveRulesEnum.NAME, "张三"));
        //对json字符串脱敏
        String jsonStr = "{\"name\":\"张三\",\"type\":\"123\",\"userDetailDTO\":{\"email\":\"1234567@qq.com\",\"cardNo\":\"1234567890\",\"idCardNo\":\"372325199901021234\"}}";
        Map<String, SensitiveRulesEnum> immutmap = ImmutableMap.of("name", SensitiveRulesEnum.NAME, "cardNo",SensitiveRulesEnum.CARD_NO, "idCardNo", SensitiveRulesEnum.CERTI_NO);
        System.out.println(SensitiveProcessUtils.dataShield(jsonStr, immutmap));
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><name>张三</name><type>123</type><userDetailDTO><email>1234567@qq.com</email><cardNo>1234567890</cardNo><idCardNo>372325199901021234</idCardNo></userDetailDTO>";
        System.out.println(SensitiveProcessUtils.dataShield(xmlStr, immutmap));
    }
}