package com.che.test.design.principle.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：余天然 on 16/5/11 下午10:33
 */
public class ImageLoader {
    //默认内存缓存，但声明的类型是接口
    ImageCache imageCache = new MemoryCache();
    //线程池，线程数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 显示图片
     *
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = imageCache.get(url);
                if (bitmap == null) {
                    bitmap = downloadImage(url);
                }
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                //添加到缓存
                imageCache.put(url, bitmap);
            }
        });
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downloadImage(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageCache getImageCache() {
        return imageCache;
    }

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }
}
