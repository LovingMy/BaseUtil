package com.che.fast_http;


import com.che.fast_http.helper.IWebLoading;
import com.che.fast_http.helper.WebException;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 网络发送者-变换器
 * <p>
 * 作者：余天然 on 16/7/18 下午10:20
 */
public class WebTransformer<T> implements Observable.Transformer<T, T> {

    private IWebLoading webLoading;
    private boolean hasTip;//是否有错误提示

    public WebTransformer() {
    }

    public WebTransformer(boolean hasTip) {
        this.hasTip = hasTip;
    }

    public WebTransformer(IWebLoading webLoading) {
        this.webLoading = webLoading;
        this.hasTip = true;
    }

    public WebTransformer(IWebLoading webLoading, boolean hasTip) {
        this.webLoading = webLoading;
        this.hasTip = hasTip;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())//在异步线程执行耗时操作
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (webLoading != null) {
                            webLoading.show();//显示进度条
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())//在主线程显示进度条
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调，更新UI
                .throttleFirst(1, TimeUnit.SECONDS)//取1秒之内的第一次,防止重复提交
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {//捕获自定义异常或系统异常
                    @Override
                    public Observable<? extends T> call(final Throwable throwable) {
                        return Observable.create(new Observable.OnSubscribe<T>() {
                            @Override
                            public void call(Subscriber<? super T> subscriber) {
                                if (hasTip) {
                                    if (throwable instanceof WebException) {
                                        subscriber.onError(throwable);
                                    } else {
                                        subscriber.onError(new WebException(throwable.getMessage()));
                                    }
                                } else {
                                    subscriber.onCompleted();
                                }
                            }
                        });
                    }
                });
    }
}
