package com.che.test.rxjava.asyncwork;

/**
 * 泛型回调
 */
public interface Callback<T> {
    void onSuccess(T result);

    void onFailure(Exception e);
}