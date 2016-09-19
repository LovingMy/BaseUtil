package com.che.test.design.structural.bridge;

/**
 * 消息发送者接口
 * <p>
 * 作者：余天然 on 16/9/13 上午1:12
 */
public interface ISender {

    void send(String msg, String toUser);
}
