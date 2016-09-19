package com.che.test.design.behavior.state;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 上午12:08
 */
public class TestClient {

    /**
     * 同一个用户只能投一票，
     * 如果用户重复投票，则重复投的票无效
     * 如果投票次数超过5次，则判定为恶意刷票，取消他所投的票；
     * 如果投票次数超过8次，则被拉入黑名单，禁止再登录和使用系统
     */
    @Test
    public void test() {
        VoteContext voteContext = new VoteContext();
        for (int i = 0; i < 9; i++) {
            voteContext.vote("小明", "萌宝宝");
        }
        //输出结果：
        //恭喜投票成功
        //请不要重复投票
        //请不要重复投票
        //请不要重复投票
        //请不要重复投票
        //你有恶意刷屏行为，取消投票资格
        //你有恶意刷屏行为，取消投票资格
        //你有恶意刷屏行为，取消投票资格
        //小明被记录到黑名单中，将禁止登录和使用本系统
    }
}
