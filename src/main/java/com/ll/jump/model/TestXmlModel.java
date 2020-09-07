package com.ll.jump.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/4/30 0030
 */
@XmlRootElement(name = "TestXml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestXmlModel implements Serializable {
    private static final long serialVersionUID = 74266789015401608L;
    @XmlAttribute(name = "type")
    private String type;
//    @XmlElement(name="name")
    private String name;
    @XmlElement(name="ageaaa")
    private String age;
    private TestXmlModel2 model2;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TestXmlModel2 getModel2() {
        return model2;
    }

    public void setModel2(TestXmlModel2 model2) {
        this.model2 = model2;
    }
}