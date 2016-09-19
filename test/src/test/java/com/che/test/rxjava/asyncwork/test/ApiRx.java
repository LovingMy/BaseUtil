package com.che.test.rxjava.asyncwork.test;

import com.che.test.rxjava.asyncwork.Callback;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yutianran on 16/7/6.
 */
public class ApiRx {
    private ApiAsync api = new ApiAsync();

    public Observable<List<News>> getLatestNews(final String tag) {
        return Observable.create(new Observable.OnSubscribe<List<News>>() {
            @Override
            public void call(final Subscriber<? super List<News>> subscriber) {
                api.getNewsList(tag, new Callback<List<News>>() {
                    @Override
                    public void onSuccess(List<News> aNewses) {
                        subscriber.onNext(aNewses);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

    public Observable<Uri> save(final News news) {
        return Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(final Subscriber<? super Uri> subscriber) {
                api.save(news, new Callback<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        subscriber.onNext(uri);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }
}
