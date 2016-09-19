package com.che.test.design.behavior.state.impl;


import com.che.test.design.behavior.state.VoteContext;
import com.che.test.design.behavior.state.VoteState;

/**
 * 黑名单投票状态
 * <p>
 * 作者：余天然 on 16/9/13 上午12:01
 */
public class BlackVoteState implements VoteState {
    @Override
    public void vote(String user, String voteItem, VoteContext voteContext) {
        //记录黑名单中，将禁止登录和使用本系统
        System.out.println(user + "被记录到黑名单中，将禁止登录和使用本系统");
    }

}
