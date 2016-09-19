package com.che.test.design.behavior.responsibility;

import com.che.test.design.behavior.responsibility.impl.Hubei;
import com.che.test.design.behavior.responsibility.impl.Guangdong;
import com.che.test.design.behavior.responsibility.impl.Hunan;
import com.che.test.design.behavior.responsibility.impl.Jiangxi;

import org.junit.Test;

/**
 * 作者：余天然 on 16/9/12 下午9:53
 */
public class TestClient {

    @Test
    public void test() {
        //生成4个处理者
        Hunan hunan = new Hunan();
        Hubei hubei = new Hubei();
        Jiangxi jiangxi = new Jiangxi();
        Guangdong guangdong = new Guangdong();

        hunan.setNextHandler(hubei);//湖南处理不了，就交给湖北
        hubei.setNextHandler(jiangxi);//湖北处理不了，就交给江西
        jiangxi.setNextHandler(guangdong);//江西处理不了，就交给广东
        guangdong.setNextHandler(null);//广东处理不了，就没人可交了

        //湖南分别处理5个请求
        hunan.handleRequest("湖南");//输出：我是湖南处理中心，只处理湖南事务
        hunan.handleRequest("湖北");//输出：我是湖北处理中心，只处理湖北事务
        hunan.handleRequest("江西");//输出：我是江西处理中心，只处理江西事务
        hunan.handleRequest("广东");//输出：我是广东处理中心，只处理广东事务
        hunan.handleRequest("西藏");//输出：我是中国处理中心，处理各省处理不了的事务
    }

}
