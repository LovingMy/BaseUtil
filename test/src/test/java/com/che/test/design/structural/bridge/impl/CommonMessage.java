package com.che.test.design.structural.bridge.impl;

import com.che.test.design.structural.bridge.AbsMessage;
import com.che.test.design.structural.bridge.ISender;

/**
 * 普通消息类
 * <p>
 * 作者：余天然 on 16/9/13 上午1:15
 */
public class CommonMessage extends AbsMessage {


    public CommonMessage(ISender ISender) {
        super(ISender);
    }

    @Override
    public void send(String msg, String toUser) {
        super.send(msg, toUser);
    }
}
