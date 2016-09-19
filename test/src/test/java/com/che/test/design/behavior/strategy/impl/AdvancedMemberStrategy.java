package com.che.test.design.behavior.strategy.impl;

import com.che.test.design.behavior.strategy.MemberStrategy;

/**
 * 高级会员策略
 * <p>
 * 作者：余天然 on 16/9/12 下午11:25
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double price) {
        System.out.println("对于高级会员的折扣为20%");
        return price * 0.8;
    }
}
