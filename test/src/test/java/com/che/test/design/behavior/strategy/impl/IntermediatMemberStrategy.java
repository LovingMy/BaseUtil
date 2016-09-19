package com.che.test.design.behavior.strategy.impl;

import com.che.test.design.behavior.strategy.MemberStrategy;

/**
 * 中级会员策略
 * <p>
 * 作者：余天然 on 16/9/12 下午11:25
 */
public class IntermediatMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double price) {
        System.out.println("对于中级会员的折扣为10%");
        return price * 0.9;
    }
}
