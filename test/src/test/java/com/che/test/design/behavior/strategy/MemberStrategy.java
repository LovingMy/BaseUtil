package com.che.test.design.behavior.strategy;

/**
 * 作者：余天然 on 16/9/12 下午11:22
 */
public interface MemberStrategy {

    /**
     * 计算优惠价
     *
     * @param price 原价
     * @return 优惠价
     */
    double calcPrice(double price);

}
