package com.ll.jump.sensitive.test;

import com.ll.jump.sensitive.SensitiveStringBuilder;
import com.ll.jump.sensitive.annotations.Sensitive;
import com.ll.jump.sensitive.enums.SensitiveRulesEnum;

import java.io.Serializable;

/**
 * 〈用户详情DTO〉
 *
 * @author LiLin
 * @date 2020/5/9 0009
 */
public class UserDetailDTO implements Serializable {
    private static final long serialVersionUID = -1471660546024258877L;
    @Sensitive(format = SensitiveRulesEnum.EMAIL)
    private String email;
    @Sensitive(ignore = true)
    private String cardNo;
    @Sensitive(format = SensitiveRulesEnum.CERTI_NO)
    private String idCardNo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    @Override
    public String toString(){
        return SensitiveStringBuilder.reflectionToString(this);
    }
}