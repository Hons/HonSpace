package com.hons.honspace.command;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testcommand")
public class TestCommand {

    String val1 = "";
    int val2 = 123;
    
    public String getVal1() {
        return val1;
    }
    @XmlElement
    public void setVal1(String val1) {
        this.val1 = val1;
    }
    public int getVal2() {
        return val2;
    }
    @XmlElement
    public void setVal2(int val2) {
        this.val2 = val2;
    }
}