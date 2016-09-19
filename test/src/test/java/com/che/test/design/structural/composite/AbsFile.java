package com.che.test.design.structural.composite;

/**
 * 抽象文件类
 * <p>
 * 作者：余天然 on 16/9/13 下午7:03
 */
public abstract class AbsFile {

    private String name;

    public AbsFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();
}
