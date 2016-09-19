package com.che.test.design.behavior.templatemethod;

import com.che.test.design.behavior.templatemethod.impl.CDAccount;
import com.che.test.design.behavior.templatemethod.impl.MoneyMarketAccount;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/13 上午12:49
 */
public class TestClient {

    /**
     * 考虑一个计算存款利息的例子。
     * 假设系统需要支持两种存款账号，即货币市场(Money Market)账号和定期存款(Certificate of Deposite)账号。
     * 这两种账号的存款利息是不同的，因此，在计算一个存户的存款利息额时，必须区分两种不同的账号类型
     */
    @Test
    public void test() {
        AbsAccount marketAccount = new MoneyMarketAccount();
        System.out.println("货币市场账户的利息为：" + marketAccount.calcInterest());

        AbsAccount cdAccount = new CDAccount();
        System.out.println("定期存款账户的利息为：" + cdAccount.calcInterest());

        //输出结果：
        //货币市场账户的利息为：399.96
        //定期存款账户的利息为：533.28
    }
}
