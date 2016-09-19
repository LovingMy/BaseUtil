package com.che.test.design.behavior.observer.impl;

import com.che.test.design.behavior.observer.IObserver;

/**
 * 作者：余天然 on 16/9/12 下午10:26
 */
public class IObserver1 implements IObserver {

    //观察者的状态
    private String observerState;

    @Override
    public void onNotify(String state) {
        this.observerState = state;
        System.out.println("我是观察者1，收到更新通知，状态为：" + state);
    }
}
