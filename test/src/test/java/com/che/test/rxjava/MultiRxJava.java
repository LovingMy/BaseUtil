package com.che.test.rxjava;

import android.text.TextUtils;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava操作符
 * <p>
 * 创建：create、just
 * 过滤:fiter
 * 变换:map、flatMap
 * 组合:concat、merge
 * 其他:timer、delay、
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MultiRxJava {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        LogUtil.setIsDebug(true);
    }

    @Test
    public void test() {
        map();

    }

    /**
     * subscribeOn
     * <p>
     * 线程切换
     */
    public void subscribeOn() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 just() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 subscribe() 的回调发生在主线程
                .subscribe(var -> LogUtil.print("number:" + var));
    }

    /**
     * map
     * <p>
     * 同步的数据变换
     */
    public void map() {
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .subscribe(s -> LogUtil.print(s));
    }


    /**
     * flatMap
     * <p>
     * 异步的数据变换
     */
    public void flatMap() {
        Observable.just("Hello, world!")
                .flatMap(s -> Observable.just(s + " -Dan"))
                .subscribe(s -> LogUtil.print(s));
    }

    /**
     * concat
     * <p>
     * 取数据时，先查找内存，再查找文件缓存，最后才查找网络
     */
    public void concat() {
        String memory = "内存";
        String file = "文件";
        String net = "网络";
        Observable<String> memoryTask = Observable.create(subscriber -> {
            if (!TextUtils.isEmpty(memory)) {
                subscriber.onNext(memory);
            } else {
                subscriber.onCompleted();
            }
        });
        Observable<String> fileTask = Observable.create(subscriber -> {
            if (!TextUtils.isEmpty(file)) {
                subscriber.onNext(file);
            } else {
                subscriber.onCompleted();
            }
        });
        Observable<String> netTask = Observable.just(net);
        //特别提醒：这里的memoryTask、fileTask千万别用just创建，否则的话会直接返回memoryTask的值(哪怕memory为空)
        Observable.concat(memoryTask, fileTask, netTask)
                .first()
                .subscribe(str -> LogUtil.print(str));
    }

    /**
     * zip
     * <p>
     * 等待多个请求完成
     */
    public void zip() {
        LogUtil.print("开始请求");
        Observable<String> getA = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("A");
                subscriber.onNext("A");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable<String> getB = Observable.create(subscriber -> {
            try {
                Thread.sleep(2000);
                LogUtil.print("B");
                subscriber.onNext("B");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable.zip(getA, getB, (a, b) -> zipAB(a, b))
                .subscribe(var -> LogUtil.print(var));
    }

    private String zipAB(String a, String b) {
        return a + "和" + b;
    }

    /**
     * combineLatest
     * <p>
     * 合并多个输入框的最新数据
     */
    public void combineLatest() {
        LogUtil.print("开始请求");
        Observable<String> userNameEt = Observable.just("YTR");
        Observable<String> passwordEt = Observable.just("123456", "");
        Observable.combineLatest(userNameEt, passwordEt, (username, password) -> validate(username, password))
                .subscribe(var -> LogUtil.print(var + ""));
    }

    private Boolean validate(String username, String password) {
        LogUtil.print("username=" + username + "\tpassword=" + password);
        if (TextUtils.isEmpty(username)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    /**
     * filter、distinct、take、reduce
     * <p>
     * 复杂的数据变换
     */
    public void filter() {
        Observable.just("1", "2", "2", "3", "4", "5")
                .map(Integer::parseInt)//转换为int
                .filter(s -> s > 1)//取大于1
                .distinct()//去重
                .take(2)//取前两位
                .reduce((integer, integer2) -> integer.intValue() + integer2.intValue())//迭代计算
                .subscribe(var -> LogUtil.print(var.toString()));//输出：5
    }

    /**
     * debounce
     * <p>
     * 获取输入框的最新数据
     */
    public void debounce() {
        LogUtil.print("开始");
        Observable<Integer> mockEt = Observable.create(subscriber -> {
            try {
                subscriber.onNext(1);
                Thread.sleep(500);
                subscriber.onNext(2);
                Thread.sleep(1500);
                subscriber.onNext(3);
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        mockEt.debounce(1, TimeUnit.SECONDS)//取1秒之内的最后一次
                .subscribe(var -> LogUtil.print(var.toString()));//输出2、3
    }

    /**
     * throttleFirst
     * <p>
     * 防止连续点击
     */
    public void throttleFirst() {
        LogUtil.print("开始");
        Observable<Integer> mockBtn = Observable.create(subscriber -> {
            try {
                subscriber.onNext(1);
                Thread.sleep(500);
                subscriber.onNext(2);
                Thread.sleep(1500);
                subscriber.onNext(3);
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        mockBtn.throttleFirst(1, TimeUnit.SECONDS)//取1秒之内的第一次
                .subscribe(var -> LogUtil.print(var.toString()));//输出1、3
    }

    /**
     * interval
     * <p>
     * 定时操作
     */
    public void interval() {
        LogUtil.print("开始");
        //延迟2秒后，每隔3秒发送一次
        Observable.interval(2, 3, TimeUnit.SECONDS)
                .subscribe(var -> LogUtil.print(var.toString()));
    }


    /**
     * defer
     * <p>
     * 包装缓慢的旧代码
     */
    public void defer() {
        //这样的话，还是会阻塞主线程
        Observable.just(blockMethod("A"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(var -> LogUtil.print(var.toString()));
        //使用defer的话，就不会阻塞主线程
        Observable.defer(() -> Observable.just(blockMethod("B")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(var -> LogUtil.print(var.toString()));
    }

    public String blockMethod(String msg) {
        String result = "block:" + msg;
        LogUtil.print(result);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 复杂任务流
     * <p>
     * ***-B-D-
     * A-      -E
     * ***--C--
     */
    public void multi() {
        Observable<String> A = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("执行任务：A");
                subscriber.onNext("结果A");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable<String> B = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("执行任务：B");
                subscriber.onNext("结果B");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable<String> C = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("执行任务：C");
                subscriber.onNext("结果C");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable<String> D = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("执行任务：D");
                subscriber.onNext("结果D");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
        Observable<String> E = Observable.create(subscriber -> {
            try {
                Thread.sleep(1000);
                LogUtil.print("执行任务：E");
                subscriber.onNext("结果E");
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                subscriber.onError(e);
            }
        });
//         -B-D-
//      A-      -E
//         --C--

        Observable<String> flatMapBD = B.subscribeOn(Schedulers.io()).flatMap(b -> D).subscribeOn(Schedulers.io());
        Observable<String> zipDC = Observable.zip(flatMapBD, C, (d, c) -> d + c).subscribeOn(Schedulers.computation());
        A.flatMap(a -> zipDC).flatMap(s -> E).subscribe();

    }


    @Test
    public void testDebounce() {
        LogUtil.print("just：debounce");
        Observable.just(1, 1)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(var -> LogUtil.print(var.toString()));

        LogUtil.print("just：throttleFirst");
        Observable.just(1, 1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(var -> LogUtil.print(var.toString()));
    }

}
