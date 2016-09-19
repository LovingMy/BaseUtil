package com.che.test.design.behavior.state.impl;

import com.che.test.design.behavior.state.VoteContext;
import com.che.test.design.behavior.state.VoteState;

/**
 * 重复投票状态
 * <p>
 * 作者：余天然 on 16/9/13 上午12:01
 */
public class RepeatVoteState implements VoteState {
    @Override
    public void vote(String user, String voteItem, VoteContext voteContext) {
        //重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }
}
