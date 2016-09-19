package com.che.test.design.behavior.command.impl;

import com.che.test.design.behavior.command.AudioPlayer;
import com.che.test.design.behavior.command.ICommand;

/**
 * 作者：余天然 on 16/9/13 上午12:36
 */
public class RewindCommand implements ICommand {
    private AudioPlayer audioPlayer;

    public RewindCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.rewind();
    }
}
