package com.che.test.design.behavior.observer;

/**
 * 作者：余天然 on 16/9/12 下午10:23
 */
public class SubjectImpl extends AbsSubject {

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState) {
        System.out.println("主题状态改变：" + newState);
        this.state = newState;
        notifyAllObserver(newState);
    }
}
