package com.che.baseutil;

import java.io.Serializable;

/**
 * 作者：余天然 on 16/9/15 下午2:34
 */
public class SPObj implements Serializable {
    private String name;
    private int age;

    public SPObj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SPObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
