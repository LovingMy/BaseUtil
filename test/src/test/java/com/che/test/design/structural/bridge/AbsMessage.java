package com.che.test.design.structural.bridge;

/**
 * 抽象消息类
 * <p>
 * 作者：余天然 on 16/9/13 上午1:12
 */
public abstract class AbsMessage {

    private ISender ISender;

    public AbsMessage(ISender ISender) {
        this.ISender = ISender;
    }

    public void send(String msg, String toUser) {
        this.ISender.send(msg, toUser);
    }
}
