package com.che.test.design.principle.impl;

import android.graphics.Bitmap;

/**
 * 图片缓存接口
 *
 * 作者：余天然 on 16/5/11 下午11:34
 */
public interface ImageCache {
    Bitmap get(String url);
    void put(String url, Bitmap bitmap);
}
