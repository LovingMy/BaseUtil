package com.che.test.design.behavior.observer;

import com.che.test.design.behavior.observer.impl.IObserver1;
import com.che.test.design.behavior.observer.impl.IObserver2;
import com.che.test.design.behavior.observer.impl.IObserver3;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/12 下午10:17
 */
public class TestClient {

    @Test
    public void test() {
        SubjectImpl subject = new SubjectImpl();
        IObserver IObserver1 = new IObserver1();
        IObserver IObserver2 = new IObserver2();
        IObserver IObserver3 = new IObserver3();

        subject.add(IObserver1);
        subject.add(IObserver2);
        subject.add(IObserver3);

        subject.change("疯魔状态");
        //输出结果：
        //主题状态改变：疯魔状态
        //我是观察者1，收到更新通知，状态为：疯魔状态
        //我是观察者2，收到更新通知，状态为：疯魔状态
        //我是观察者3，收到更新通知，状态为：疯魔状态
    }
}
