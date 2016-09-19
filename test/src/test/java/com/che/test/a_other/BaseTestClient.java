package com.che.test.a_other;

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

import static com.google.common.truth.Truth.assertThat;

/**
 * Robolectric的配置方法
 * <p>
 * 作者：余天然 on 16/9/18 下午3:54
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class BaseTestClient {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
    }

    @Test
    public void test() {
        int a = 2 + 2;
        LogUtil.print("a=" + a);
        assertThat(a).isEqualTo(4);
    }

}
