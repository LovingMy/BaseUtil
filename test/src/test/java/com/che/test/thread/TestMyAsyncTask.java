package com.che.test.thread;

import android.app.Application;

import com.che.base_util.LogUtil;
import com.che.test.BuildConfig;
import com.che.test.thread.impl.MyAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static com.google.common.truth.Truth.assertThat;

/**
 * 测试自定义异步任务类
 * <p>
 * 作者：余天然 on 16/9/18 下午3:54
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestMyAsyncTask {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;//用Android.Log代替System.out
        Application application = RuntimeEnvironment.application;//获取应用Context
    }


    @Test
    public void test() {
        MyAsyncTask<String> asyncTask = new MyAsyncTask<String>() {
            @Override
            protected void onPreExecute() {
                LogUtil.print("");
            }

            @Override
            protected String doInBackground() {
                LogUtil.print("");
                return "daibi";
            }

            @Override
            protected void onPostExecute(String param) {
                LogUtil.print("");
                assertThat(param).isEqualTo("daibi");
            }
        };
        asyncTask.execute();

    }

}
