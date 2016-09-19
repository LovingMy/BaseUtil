package com.che.test.design.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹类
 * <p>
 * 作者：余天然 on 16/9/13 下午7:04
 */
public class Folder extends AbsFile {

    private List<AbsFile> files;

    public Folder(String name) {
        super(name);
        files=new ArrayList<>();
    }

    /**
     * 浏览文件夹中的文件
     */
    @Override
    public void display() {
        for (AbsFile file : files) {
            file.display();
        }
    }

    /**
     * 添加文件
     *
     * @param file
     */
    public void add(AbsFile file) {
        files.add(file);
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public void remove(AbsFile file) {
        files.remove(file);
    }

}

