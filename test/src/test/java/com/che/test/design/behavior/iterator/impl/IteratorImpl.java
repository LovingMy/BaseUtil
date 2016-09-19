package com.che.test.design.behavior.iterator.impl;

import com.che.test.design.behavior.iterator.Iterator;
import com.che.test.design.behavior.iterator.Collection;

/**
 * 作者：余天然 on 16/9/13 下午6:20
 */
public class IteratorImpl<T> implements Iterator<T> {

    private Collection<T> collection;
    private int pos = -1;

    public IteratorImpl(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public T previous() {
        if (pos > 0) {
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public T next() {
        if (pos < collection.size() - 1) {
            pos++;
        }
        return collection.get(pos);
    }

    @Override
    public T first() {
        pos = 0;
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        if (pos < collection.size() - 1) {
            return true;
        } else {
            return false;
        }
    }
}
