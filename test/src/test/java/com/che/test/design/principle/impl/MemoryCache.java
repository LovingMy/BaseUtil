package com.che.test.design.principle.impl;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 作者：余天然 on 16/5/11 下午10:59
 */
public class MemoryCache implements ImageCache {
    //图片缓存
    LruCache<String, Bitmap> cache;

    public MemoryCache() {
        init();
    }

    private void init() {
        //CPU最大可用内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //应用缓存
        final int cacheSize = maxMemory / 4;
        cache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return cache.get(url);
    }

}
