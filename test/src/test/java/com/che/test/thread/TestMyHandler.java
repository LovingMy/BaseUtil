package com.che.test.thread;

import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;
import com.che.test.thread.impl.MyHandler;
import com.che.test.thread.impl.MyLooper;
import com.che.test.thread.impl.MyMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 测试自定义Handler
 * <p>
 * 模拟子线程请求网络
 * <p>
 * Created by yutianran on 16/7/4.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestMyHandler {

    private MyHandler mainHandler;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
    }

    @Test
    public void test() {
        //初始化主线程Looper
        MyLooper.prepare();
        mainHandler = new MyHandler(new MyHandler.CallBack() {
            @Override
            public void handleMessage(MyMessage msg) {
                // 刷新界面
                String obj = (String) msg.obj;
                LogUtil.print("刷新界面:" + obj);
            }
        });
        //发起网络请求
        LogUtil.print("在主线程发起一个网络请求");
        NetThread netThread = new NetThread("http://baidu.com");
        netThread.start();
        LogUtil.print("在主线程继续其它操作");

        //开始消息循环
        MyLooper.loop();
    }


    //网络线程类
    private class NetThread extends Thread {
        private String url;

        public NetThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            String body = getWebData(url);
            MyMessage msg = new MyMessage();
            msg.obj = body;
            mainHandler.sendMessage(msg);
        }
    }

    //执行网络请求
    private String getWebData(String url) {
        LogUtil.print("执行请求网络:" + url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = "这是" + url + "的响应值";
        LogUtil.print("请求网络成功:" + body);
        return body;
    }
}
