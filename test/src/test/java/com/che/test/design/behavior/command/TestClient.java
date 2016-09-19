package com.che.test.design.behavior.command;

import com.che.test.design.behavior.command.impl.PlayCommand;
import com.che.test.design.behavior.command.impl.RewindCommand;
import com.che.test.design.behavior.command.impl.StopCommand;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 上午12:31
 */
public class TestClient {

    /**
     * 小女孩茱丽(Julia)有一个盒式录音机，此录音机有播音(Play)、倒带(Rewind)和停止(Stop)功能，
     * 录音机的键盘便是请求者(Invoker)角色；茱丽(Julia)是客户端角色，而录音机便是接收者角色。
     * Command类扮演抽象命令角色，而PlayCommand、StopCommand和RewindCommand便是具体命令类。
     * 茱丽不需要知道播音、倒带和停止是怎么具体执行的，执行细节由键盘(Keypad)具体实现。
     * 茱丽只需要在键盘上按下相应的键便可以了
     */
    @Test
    public void test() {
        //创建接受者
        AudioPlayer audioPlayer = new AudioPlayer();

        //创建命令
        ICommand playCcommand = new PlayCommand(audioPlayer);
        ICommand rewindCommand = new RewindCommand(audioPlayer);
        ICommand stopCommand = new StopCommand(audioPlayer);

        //创建调用者
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCcommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setStopCommand(stopCommand);

        //开始测试
        keypad.play();
        keypad.rewind();
        keypad.stop();
        keypad.play();

        //输出结果：
        //播放……
        //倒带……
        //停止……
        //播放……

    }
}
