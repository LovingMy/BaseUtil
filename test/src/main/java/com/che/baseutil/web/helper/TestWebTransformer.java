package com.che.baseutil.web.helper;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * 网络发送者-变换器
 * <p>
 * 测试用，去掉了线程切换
 * <p>
 * 作者：余天然 on 16/7/18 下午10:20
 */
public class TestWebTransformer<T> implements Observable.Transformer<T, T> {

    private IWebLoading webLoading;
    private boolean hasTip;//是否有错误提示

    public TestWebTransformer() {
    }

    public TestWebTransformer(boolean hasTip) {
        this.hasTip = hasTip;
    }

    public TestWebTransformer(IWebLoading webLoading) {
        this.webLoading = webLoading;
        this.hasTip = true;
    }

    public TestWebTransformer(IWebLoading webLoading, boolean hasTip) {
        this.webLoading = webLoading;
        this.hasTip = hasTip;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (webLoading != null) {
                            webLoading.show();//显示进度条
                        }
                    }
                })
                .throttleFirst(1, TimeUnit.SECONDS)//取1秒之内的第一次,防止重复提交
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {//捕获自定义异常或系统异常
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
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
