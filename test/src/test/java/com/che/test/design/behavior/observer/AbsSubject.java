package com.che.test.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余天然 on 16/9/12 下午10:10
 */
public abstract class AbsSubject {

    private List<IObserver> list = new ArrayList<>();

    public void add(IObserver IObserver) {
        list.add(IObserver);
    }

    public void remove(IObserver IObserver) {
        list.remove(IObserver);
    }

    public void notifyAllObserver(String newState) {
        if (list.isEmpty()) return;
        for (IObserver IObserver : list) {
            IObserver.onNotify(newState);
        }
    }
}
