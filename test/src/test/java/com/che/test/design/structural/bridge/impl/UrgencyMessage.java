package com.che.test.design.structural.bridge.impl;

import com.che.test.design.structural.bridge.AbsMessage;

/**
 * 加急消息类
 * <p>
 * 作者：余天然 on 16/9/13 上午1:16
 */
public class UrgencyMessage extends AbsMessage {

    public UrgencyMessage(com.che.test.design.structural.bridge.ISender ISender) {
        super(ISender);
    }

    @Override
    public void send(String msg, String toUser) {
        msg = "加急：" + msg;
        super.send(msg, toUser);
    }

    /**
     * 监控消息的处理状态
     *
     * @param messageId 消息id
     * @return 处理状态
     */
    public Object watch(String messageId) {
        return null;
    }

}
