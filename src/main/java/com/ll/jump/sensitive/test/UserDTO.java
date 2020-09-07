package com.ll.jump.sensitive.test;

import com.ll.jump.sensitive.SensitiveStringBuilder;
import com.ll.jump.sensitive.annotations.Sensitive;
import com.ll.jump.sensitive.enums.SensitiveRulesEnum;

import java.io.Serializable;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/5/9 0009
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -4868334758791881553L;
    @Sensitive(format = SensitiveRulesEnum.NAME)
    private String name;
    private String type;
    private UserDetailDTO userDetailDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return SensitiveStringBuilder.reflectionToString(this);
    }

    public UserDetailDTO getUserDetailDTO() {
        return userDetailDTO;
    }

    public void setUserDetailDTO(UserDetailDTO userDetailDTO) {
        this.userDetailDTO = userDetailDTO;
    }
}