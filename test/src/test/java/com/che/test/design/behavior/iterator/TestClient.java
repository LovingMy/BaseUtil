package com.che.test.design.behavior.iterator;

import com.che.test.design.behavior.iterator.impl.CollectionImpl;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 下午6:18
 */
public class TestClient {

    /**
     * 自定义集合和迭代器
     */
    @Test
    public void test() {
        String[] strings = {"A", "B", "C", "D", "E"};
        Collection<String> collection = new CollectionImpl(strings);
        Iterator<String> iterator = collection.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //输出结果：
        //A
        //B
        //C
        //D
        //E
    }
}
