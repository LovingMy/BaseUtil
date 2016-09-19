package com.che.test.design.principle.impl;

import android.graphics.Bitmap;

/**
 * 作者：余天然 on 16/5/11 下午11:31
 */
public class DoubleCache implements ImageCache {
    MemoryCache memoryCache = new MemoryCache();
    DiskCache diskCache = new DiskCache();

    //先从内存缓存获取图片，如果没有，再从SD卡中获取
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
