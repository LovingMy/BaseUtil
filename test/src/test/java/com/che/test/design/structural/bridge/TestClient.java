package com.che.test.design.structural.bridge;

import com.che.test.design.structural.bridge.impl.CommonMessage;
import com.che.test.design.structural.bridge.impl.EmailSender;
import com.che.test.design.structural.bridge.impl.SmsSender;
import com.che.test.design.structural.bridge.impl.UrgencyMessage;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 上午1:07
 */
public class TestClient {

    /**
     * 发送提示消息
     * <p>
     * 从业务上看，消息又分成普通消息、加急消息和特急消息多种
     * 加急消息:是在消息上添加加急，
     * 特急消息:除了添加特急外，还会做一条催促的记录，多久不完成会继续催促；
     * <p>
     * 从发送消息的手段上看，又有系统内短消息、手机短信息、邮件等。
     */
    @Test
    public void test() {
        //创建具体的消息发送器
        ISender sender = new SmsSender();

        //创建普通消息
        AbsMessage message = new CommonMessage(sender);
        message.send("加班申请速批", "李总");

        //将实现方式切换成邮件，再次发送
        sender = new EmailSender();

        //创建加急消息
        message = new UrgencyMessage(sender);
        message.send("加班申请速批", "李总");

        //输出结果：
        //使用系统内短消息的方法，发送消息'加班申请速批'给李总
        //使用邮件消息的方法，发送消息'加急：加班申请速批'给李总
    }
}
