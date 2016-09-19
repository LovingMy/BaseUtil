package com.che.test.thread.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * 作者：余天然 on 16/9/19 下午4:48
 */
public abstract class MyAsyncTask<T> {

    private static final HandlerThread handlerThread = new HandlerThread(MyAsyncTask.class.getName(), 10);

    public MyAsyncTask() {
    }

    protected abstract void onPreExecute();

    protected abstract T doInBackground();

    protected abstract void onPostExecute(T param);

    public final MyAsyncTask<T> execute() {
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        Handler bgHandler = new Handler(handlerThread.getLooper());
        this.onPreExecute();
        bgHandler.post(new Runnable() {
            public void run() {
                final T param = MyAsyncTask.this.doInBackground();
                mainHandler.post(new Runnable() {
                    public void run() {
                        MyAsyncTask.this.onPostExecute(param);
                    }
                });
            }
        });
        return this;
    }

    static {
        handlerThread.start();
    }
}
