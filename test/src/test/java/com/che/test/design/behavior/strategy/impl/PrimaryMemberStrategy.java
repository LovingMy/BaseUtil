package com.che.test.design.behavior.strategy.impl;

import com.che.test.design.behavior.strategy.MemberStrategy;

/**
 * 初级会员策略
 * <p>
 * 作者：余天然 on 16/9/12 下午11:25
 */
public class PrimaryMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double price) {
        System.out.println("对于初级会员没有折扣");
        return price;
    }
}
