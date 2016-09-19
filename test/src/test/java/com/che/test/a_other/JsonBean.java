package com.che.test.a_other;

/**
 * 作者：余天然 on 16/9/19 下午3:46
 */
public class JsonBean {
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
