package com.che.test.design.behavior.strategy;

import com.che.test.design.behavior.strategy.impl.AdvancedMemberStrategy;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/12 下午11:22
 */
public class TestClient {

    @Test
    public void test() {
        //创建策略类
        MemberStrategy strategy = new AdvancedMemberStrategy();
        //创建环境类
        Price price = new Price(strategy);
        //使用环境类
        double quote = price.quote(300);
        System.out.println("商品的优惠价为：" + quote);
        //输出结果：
        //对于高级会员的折扣为20%
        //商品的优惠价为：240.0
    }
}
