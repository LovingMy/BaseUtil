package com.che.test.design.principle;


import android.graphics.Bitmap;

import com.che.test.design.principle.impl.ImageCache;
import com.che.test.design.principle.impl.ImageLoader;
import com.che.test.design.principle.impl.MemoryCache;

import org.junit.Test;


/**
 * 作者：余天然 on 16/5/11 下午11:40
 */
public class ImageLoaderTest {

    @Test
    public void test() {
        ImageLoader imageLoader = new ImageLoader();
        //使用内存缓存
        imageLoader.setImageCache(new MemoryCache());
        //使用SD卡缓存
        imageLoader.setImageCache(new MemoryCache());
        //使用双缓存
        imageLoader.setImageCache(new MemoryCache());
        //使用自定义缓存
        imageLoader.setImageCache(new ImageCache() {
            @Override
            public Bitmap get(String url) {
                return null;
            }

            @Override
            public void put(String url, Bitmap bitmap) {

            }
        });
    }
}
