package com.che.fast_http;


import com.che.fast_http.helper.IWebLoading;
import com.che.fast_http.helper.WebException;

import rx.Subscriber;

/**
 * 网络抽象接受者
 * <p>
 * 作者：余天然 on 16/7/11 下午11:52
 */
public abstract class AbsWebSubscriber<T> extends Subscriber<T> {

    private IWebLoading webLoading;

    /**
     * 带进度条
     */
    public AbsWebSubscriber() {
    }

    /**
     * 不带进度条
     *
     * @param webLoading
     */
    public AbsWebSubscriber(IWebLoading webLoading) {
        this.webLoading = webLoading;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
        //隐藏进度条
        if (webLoading != null) {
            webLoading.dismiss();
        }
        //检查是否是业务错误
        if (checkIsBizFailure(t)) {
            //先调用业务错误
            WebException exception = onBizError(t);
            //再调用请求失败
            onFailure(exception);
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        //隐藏进度条
        if (webLoading != null) {
            webLoading.dismiss();
        }
        //先调用业务错误
        WebException exception = onWebError(e);
        //再调用请求失败
        onError(exception);
    }


    /**
     * 检查是否是业务失败
     *
     * @param t
     * @return
     */
    protected boolean checkIsBizFailure(T t) {
        return false;
    }

    /**
     * 请求成功
     *
     * @param t
     */
    public abstract void onSuccess(T t);


    /**
     * 请求到数据了，但业务错误
     *
     * @param t
     * @return WebException
     */
    public WebException onBizError(T t) {
        return new WebException("业务错误");
    }

    /**
     * 没请求成功，网络错误
     *
     * @param e
     */
    public WebException onWebError(Throwable e) {
        return new WebException("网络错误");
    }

    /**
     * 请求失败
     * <p>
     * 不管是业务错误，还是网络错误，都是请求失败
     *
     * @param exception
     */
    public void onFailure(WebException exception) {

    }

}
