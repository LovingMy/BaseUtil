package com.che.test.design.structural.composite.impl;

import com.che.test.design.structural.composite.AbsFile;

/**
 * 作者：余天然 on 16/9/13 下午7:07
 */
public class VideoFile extends AbsFile {

    public VideoFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("这是视频文件，文件名：" + super.getName());
    }
}
