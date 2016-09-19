package com.che.test.rxjava;

import com.che.base_util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.subjects.AsyncSubject;

/**
 * 作者：余天然 on 16/7/12 下午3:28
 */
public class SimpleRxJava {

    public static void main(String[] args) {
        test01();
//        test02();
//        test03();
//        test04();
    }

    private static void test04() {
        AsyncSubject<String> subject = AsyncSubject.create();
//        ReplaySubject<String> subject = ReplaySubject.create();
//        BehaviorSubject<String> subject = BehaviorSubject.create();
//        PublishSubject<String> subject = PublishSubject.create();
        Subscription subscription = subject.subscribe(new MySubscriber<String>());
        subject.onNext("test01");
        subject.onNext("test02");
    }

    private static void test03() {
        Observable<String> observable = Observable.just("helloWorld");
        Subscription subscription = observable.subscribe(new MySubscriber<String>());
    }


    static int retry = 0;

    private static void test02() {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(10);
        items.add(100);
        items.add(200);

        Observable<Integer> observableString = Observable.from(items);
        Subscription subscription = observableString.subscribe();
    }

    //1-测试onCompleted、onNext、onError方法的调用时机
    private static void test01() {
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                for (int i = 0; i < 5; i++) {
                    if (i == 3) {
                        throw new RuntimeException("不能为3");
                    } else {
                        observer.onNext(i);
                    }
                }
                observer.onCompleted();
            }
        });

        Subscription subscription = observable
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
                    @Override
                    public Observable<? extends Integer> call(Throwable throwable) {
                        LogUtil.print("retry="+retry);
//                        if (retry < 3) {
//                            retry++;
//                            ;
//                        }
                        return  observable;
                    }
                })
                .subscribe(new MySubscriber<Integer>());
    }

    //消息接收器
    public static class MySubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {
            LogUtil.print("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.print("onError:" + e.getMessage());
        }

        @Override
        public void onNext(T t) {
            LogUtil.print("onNext:" + t.toString());
        }
    }
}
