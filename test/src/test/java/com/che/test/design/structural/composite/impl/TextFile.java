package com.che.test.design.structural.composite.impl;

import com.che.test.design.structural.composite.AbsFile;

/**
 * 作者：余天然 on 16/9/13 下午7:07
 */
public class TextFile extends AbsFile {

    public TextFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("这是文本文件，文件名：" + super.getName());
    }
}
