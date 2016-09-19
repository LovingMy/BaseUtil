package com.che.test.design.behavior.templatemethod.impl;

import com.che.test.design.behavior.templatemethod.AbsAccount;

/**
 * 货币市场账户
 * <p>
 * 作者：余天然 on 16/9/13 上午12:58
 */
public class MoneyMarketAccount extends AbsAccount {
    @Override
    protected String getAccountType() {
        return "Money Market";
    }

    @Override
    protected double getInterestRate() {
        return 0.045;
    }
}
