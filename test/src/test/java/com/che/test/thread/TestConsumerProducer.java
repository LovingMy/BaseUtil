package com.che.test.thread;

import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 测试生产者消费者模型
 * <p>
 * 作者：余天然 on 16/6/29 下午8:49
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestConsumerProducer {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
    }

    @Test
    public void test() {
        Godown godown = new Godown(30);

        Consumer c1 = new Consumer(50, godown);
        Consumer c2 = new Consumer(20, godown);
        Consumer c3 = new Consumer(30, godown);
        Producer p1 = new Producer(10, godown);
        Producer p2 = new Producer(30, godown);
        Producer p3 = new Producer(20, godown);
        Producer p4 = new Producer(10, godown);
        Producer p5 = new Producer(10, godown);
        Producer p6 = new Producer(10, godown);
        Producer p7 = new Producer(80, godown);

        c1.setName("c1");
        c2.setName("c2");
        c3.setName("c3");
        p1.setName("p1");
        p2.setName("p2");
        p3.setName("p3");
        p4.setName("p4");
        p5.setName("p5");
        p6.setName("p6");
        p7.setName("p7");

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }

    //生产者
    private static class Producer extends Thread {
        private Godown godown;
        private int num;

        public Producer(int num, Godown godown) {
            this.num = num;
            this.godown = godown;
        }

        @Override
        public void run() {
            try {
                godown.produce(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费者
    private static class Consumer extends Thread {
        private Godown godown;
        private int num;

        public Consumer(int num, Godown godown) {
            this.num = num;
            this.godown = godown;
        }

        @Override
        public void run() {
            try {
                godown.consume(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //仓库
    private static class Godown {
        private int maxNum = 100;//最大库存量
        private int curNum;//当前库存量

        public Godown(int curNum) {
            this.curNum = curNum;
        }

        //生产指定数量的产品
        public synchronized void produce(int num) throws InterruptedException {
            while (curNum + num > maxNum) {
                LogUtil.print("不可生产！准备生产：" + num + "\t剩余库存：" + (maxNum - curNum));
                wait();
            }
            curNum += num;
            LogUtil.print("生产：" + num + "\t当前库存：" + curNum);
            notifyAll();
        }

        //消费指定数量的产品
        public synchronized void consume(int num) throws InterruptedException {
            while (curNum < num) {
                LogUtil.print("不可消费！准备消费：" + num + "\t当前库存：" + curNum);
                wait();
            }
            curNum -= num;
            LogUtil.print("消费：" + num + "\t当前库存：" + curNum);
            notifyAll();
        }

    }

    //TestPhoneNumber-01
//    Thread:c1	不可消费！准备消费：50	当前库存：30
//    Thread:p1	生产：10	当前库存：40
//    Thread:c3	消费：30	当前库存：10
//    Thread:c2	不可消费！准备消费：20	当前库存：10
//    Thread:p3	生产：20	当前库存：30
//    Thread:p2	生产：30	当前库存：60
//    Thread:c1	消费：50	当前库存：10
//    Thread:p4	生产：10	当前库存：20
//    Thread:c2	消费：20	当前库存：0
//    Thread:p5	生产：10	当前库存：10
//    Thread:p6	生产：10	当前库存：20
//    Thread:p7	生产：80	当前库存：100

    //TestPhoneNumber-02
//    Thread:c1	不可消费！准备消费：50	当前库存：30
//    Thread:c3	消费：30	当前库存：0
//    Thread:c2	不可消费！准备消费：20	当前库存：0
//    Thread:p2	生产：30	当前库存：30
//    Thread:c1	不可消费！准备消费：50	当前库存：30
//    Thread:p1	生产：10	当前库存：40
//    Thread:c1	不可消费！准备消费：50	当前库存：40
//    Thread:c2	消费：20	当前库存：20
//    Thread:c1	不可消费！准备消费：50	当前库存：20
//    Thread:p3	生产：20	当前库存：40
//    Thread:p4	生产：10	当前库存：50
//    Thread:c1	消费：50	当前库存：0
//    Thread:p5	生产：10	当前库存：10
//    Thread:p6	生产：10	当前库存：20
//    Thread:p7	生产：80	当前库存：100

}
