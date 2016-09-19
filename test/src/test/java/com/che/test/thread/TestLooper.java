package com.che.test.thread;


import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;
import com.che.test.thread.impl.LooperThread;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 测试LooperThread
 * <p>
 * 作者：余天然 on 16/6/29 下午7:18
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestLooper {


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
    }

    @Test
    public void test() {
        LooperThread looperThread = new LooperThread(new LooperThread.Callbak() {
            @Override
            public boolean handleMessage(LooperThread.Message msg) {
                LogUtil.print("handleMessage:" + msg.what + "\tthread:" + Thread.currentThread().getName());
                return false;
            }
        });
        looperThread.start();

        LooperThread.Message msg1 = new LooperThread.Message(1);
        LogUtil.print("sendMessage:" + msg1.what + "\tthread:" + Thread.currentThread().getName());
        looperThread.sendMessage(msg1);

        LooperThread.Message msg2 = new LooperThread.Message(2);
        LogUtil.print("sendMessage:" + msg2.what + "\tthread:" + Thread.currentThread().getName());
        looperThread.sendMessage(msg2);
    }
}
