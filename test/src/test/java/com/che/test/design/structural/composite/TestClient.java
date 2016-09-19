package com.che.test.design.structural.composite;

import com.che.test.design.structural.composite.impl.ImageFile;
import com.che.test.design.structural.composite.impl.TextFile;
import com.che.test.design.structural.composite.impl.VideoFile;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 下午7:09
 */
public class TestClient {

    @Test
    public void test() {
        /**
         * 我们先建立一个这样的文件系统:
         *                  总文件夹
         *
         *   a.txt    b.jpg                   c文件夹
         *                      c_1.text  c_1.rmvb    c_1.jpg
         *
         */
        //构造总文件夹
        Folder rootFolder = new Folder("总文件夹");
        TextFile aText = new TextFile("a.txt");
        ImageFile bImage = new ImageFile("b.jpg");
        Folder cFolder = new Folder("c文件夹");
        rootFolder.add(aText);
        rootFolder.add(bImage);
        rootFolder.add(cFolder);

        //构造c文件夹
        TextFile cText = new TextFile("c_1.text");
        VideoFile cVideo = new VideoFile("c_1.rmvb");
        ImageFile cImage = new ImageFile("c_1.jpg");
        cFolder.add(cText);
        cFolder.add(cVideo);
        cFolder.add(cImage);

        //遍历c文件夹
        cFolder.display();

        System.out.println("-----------------------");

        //将c_1.txt删除，再遍历根文件夹
        cFolder.remove(cText);
        rootFolder.display();

        //输出结果：
        //这是文本文件，文件名：c_1.text
        //这是视频文件，文件名：c_1.rmvb
        //这是图片文件，文件名：c_1.jpg
        //-----------------------
        //这是文本文件，文件名：a.txt
        //这是图片文件，文件名：b.jpg
        //这是视频文件，文件名：c_1.rmvb
        //这是图片文件，文件名：c_1.jpg
    }
}
