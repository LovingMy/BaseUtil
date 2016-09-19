package com.che.test.design.structural.bridge.impl;

import com.che.test.design.structural.bridge.ISender;

/**
 * 手机消息发送器
 * <p>
 * 作者：余天然 on 16/9/13 上午1:19
 */
public class MobileSender implements ISender {
    @Override
    public void send(String msg, String toUser) {
        System.out.println("使用手机消息的方法，发送消息'" + msg + "'给" + toUser);
    }
}
