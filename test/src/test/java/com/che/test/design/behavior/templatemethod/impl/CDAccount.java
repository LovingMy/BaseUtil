package com.che.test.design.behavior.templatemethod.impl;

import com.che.test.design.behavior.templatemethod.AbsAccount;

/**
 * 定期存款账户
 * <p>
 * 作者：余天然 on 16/9/13 上午12:59
 */
public class CDAccount extends AbsAccount {
    @Override
    protected String getAccountType() {
        return "Certificate of Deposite";
    }

    @Override
    protected double getInterestRate() {
        return 0.06;
    }
}
