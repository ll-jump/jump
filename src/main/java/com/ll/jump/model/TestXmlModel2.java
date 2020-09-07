package com.ll.jump.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/30 0030
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class TestXmlModel2 implements Serializable {
    private static final long serialVersionUID = -8274764853138414920L;
    //    @XmlAttribute(name = "type")
    private String type2;
//    @XmlElement(name="name")
    private String name2;
    @XmlElement(name="")
    private String age2;

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getAge2() {
        return age2;
    }

    public void setAge2(String age2) {
        this.age2 = age2;
    }

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }

}