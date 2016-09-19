package com.che.test.design.behavior.command;

/**
 * 作者：余天然 on 16/9/13 上午12:38
 */
public class Keypad {
    private ICommand playCommand;
    private ICommand rewindCommand;
    private ICommand stopCommand;

    public void setPlayCommand(ICommand playCommand) {
        this.playCommand = playCommand;
    }

    public void setRewindCommand(ICommand rewindCommand) {
        this.rewindCommand = rewindCommand;
    }

    public void setStopCommand(ICommand stopCommand) {
        this.stopCommand = stopCommand;
    }

    public void play() {
        playCommand.execute();
    }

    public void rewind() {
        rewindCommand.execute();
    }

    public void stop() {
        stopCommand.execute();
    }
}
