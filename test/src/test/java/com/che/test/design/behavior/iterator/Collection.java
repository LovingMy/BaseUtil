package com.che.test.design.behavior.iterator;

/**
 * 作者：余天然 on 16/9/13 下午6:15
 */
public interface Collection<T> {

    Iterator<T> iterator();

    T get(int i);

    int size();
}
