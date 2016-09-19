package com.che.test.design.behavior.state.impl;


import com.che.test.design.behavior.state.VoteContext;
import com.che.test.design.behavior.state.VoteState;

/**
 * 正常投票状态
 * <p>
 * 作者：余天然 on 16/9/12 下午11:50
 */
public class NormalVoteState implements VoteState {
    @Override
    public void vote(String user, String voteItem, VoteContext voteContext) {
        //正常投票，记录到投票记录中
        voteContext.getVoteMap().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }
}
