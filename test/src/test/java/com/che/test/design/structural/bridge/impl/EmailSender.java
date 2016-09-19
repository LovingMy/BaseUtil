package com.che.test.design.structural.bridge.impl;

import com.che.test.design.structural.bridge.ISender;

/**
 * 邮件消息发送器
 * <p>
 * 作者：余天然 on 16/9/13 上午1:19
 */
public class EmailSender implements ISender {
    @Override
    public void send(String msg, String toUser) {
        System.out.println("使用邮件消息的方法，发送消息'" + msg + "'给" + toUser);
    }
}
