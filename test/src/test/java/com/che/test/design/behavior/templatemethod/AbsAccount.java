package com.che.test.design.behavior.templatemethod;

/**
 * 作者：余天然 on 16/9/13 上午12:51
 */
public abstract class AbsAccount {

    /**
     * 计算利息
     *
     * @return
     */
    public final double calcInterest() {
        double interestRate = getInterestRate();
        String accountType = getAccountType();
        double account = calcAccount(accountType);
        return account * interestRate;
    }

    /**
     * 计算存款
     *
     * @param accountType
     * @return
     */
    private double calcAccount(String accountType) {
        //省略相关业务代码
        return 8888;
    }

    protected abstract String getAccountType();

    protected abstract double getInterestRate();
}
