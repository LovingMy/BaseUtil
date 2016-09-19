package com.che.test.design.behavior.state;

import com.che.test.design.behavior.state.impl.NormalVoteState;
import com.che.test.design.behavior.state.impl.BlackVoteState;
import com.che.test.design.behavior.state.impl.RepeatVoteState;
import com.che.test.design.behavior.state.impl.SpiteVoteState;

import java.util.HashMap;
import java.util.Map;

/**
 * 投票环境类
 * <p>
 * 作者：余天然 on 16/9/12 下午11:48
 */
public class VoteContext {

    private VoteState state;
    private Map<String, String> voteMap;//用户投票项
    private Map<String, Integer> voteCountMap;//用户投票次数

    public VoteContext() {
        voteMap = new HashMap<>();
        voteCountMap = new HashMap<>();
    }

    public Map<String, String> getVoteMap() {
        return voteMap;
    }

    /**
     * 投票
     *
     * @param user     投票人
     * @param voteItem 投票项
     */
    public void vote(String user, String voteItem) {
        //1.为该用户增加投票次数
        Integer oldVoteCount = voteCountMap.get(user);
        if (oldVoteCount == null) {
            oldVoteCount = 0;
        }
        oldVoteCount++;
        voteCountMap.put(user, oldVoteCount);

        //2.判断该用户的投票状态
        if (oldVoteCount == 1) {
            state = new NormalVoteState();
        } else if (oldVoteCount > 1 && oldVoteCount <=5) {
            state = new RepeatVoteState();
        } else if (oldVoteCount > 5 && oldVoteCount <=8) {
            state = new SpiteVoteState();
        } else if (oldVoteCount > 8) {
            state = new BlackVoteState();
        }
        state.vote(user, voteItem, this);
    }
}
