package com.che.test.design.behavior.strategy;

/**
 * 作者：余天然 on 16/9/12 下午11:29
 */
public class Price {

    private MemberStrategy strategy;

    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    public double quote(double price) {
        return this.strategy.calcPrice(price);
    }

}
