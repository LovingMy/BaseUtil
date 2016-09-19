package com.che.test.design.behavior.iterator;

/**
 * 作者：余天然 on 16/9/13 下午6:12
 */
public interface Iterator<T> {

    T previous();//前移

    T next();//后移

    T first();//取得第一个元素

    boolean hasNext();

}
