package com.che.test.design.behavior.state.impl;

import com.che.test.design.behavior.state.VoteContext;
import com.che.test.design.behavior.state.VoteState;

/**
 * 恶意投票状态
 * <p>
 * 作者：余天然 on 16/9/13 上午12:01
 */
public class SpiteVoteState implements VoteState {
    @Override
    public void vote(String user, String voteItem, VoteContext voteContext) {
        //恶意投票，取消用户的投票资格，并取消投票记录
        String str = voteContext.getVoteMap().get(user);
        if (str != null) {
            voteContext.getVoteMap().remove(user);
        }
        System.out.println("你有恶意刷屏行为，取消投票资格");
    }
}
