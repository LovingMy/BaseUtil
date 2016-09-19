package com.che.test.design.behavior.state;

/**
 * 投票状态类
 * <p>
 * 作者：余天然 on 16/9/12 下午11:46
 */
public interface VoteState {

    /**
     * 处理状态对应的行为
     *
     * @param user        投票人
     * @param voteItem    投票项
     * @param voteContext 投票上下文，用来在实现状态对应的功能处理的时候，可以回调上下文的数据
     */
    void vote(String user, String voteItem, VoteContext voteContext);
}
