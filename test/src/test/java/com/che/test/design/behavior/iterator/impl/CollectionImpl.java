package com.che.test.design.behavior.iterator.impl;

import com.che.test.design.behavior.iterator.Iterator;
import com.che.test.design.behavior.iterator.Collection;

/**
 * 作者：余天然 on 16/9/13 下午6:19
 */
public class CollectionImpl implements Collection<String> {

    private String[] strings;

    public CollectionImpl(String[] strings) {
        this.strings = strings;
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(this);
    }

    @Override
    public String get(int i) {
        return strings[i];
    }

    @Override
    public int size() {
        return strings.length;
    }
}
