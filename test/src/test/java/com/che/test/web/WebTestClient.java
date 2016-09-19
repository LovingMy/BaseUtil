package com.che.test.web;

import com.che.base_util.LogUtil;
import com.che.fast_http.AbsWebSubscriber;
import com.che.fast_http.WebClient;
import com.che.fast_http.helper.IWebLoading;
import com.che.fast_http.helper.TestWebLoading;
import com.che.fast_http.helper.TestWebTransformer;
import com.che.test.BuildConfig;
import com.che.test.web.bean.TestLoginRequest;
import com.che.test.web.bean.TestLoginResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static com.google.common.truth.Truth.assertThat;

/**
 * 作者：余天然 on 16/9/16 下午5:14
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class WebTestClient {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    /**
     * 测试登录请求
     */
    @Test
    public void testLogin() {
        //创建网络进度条
        IWebLoading webLoading = new TestWebLoading();
        //创建网络接口
        WebInterface webInterface = new WebClient().getWebInterface(WebInterface.class);
        //执行登录请求
        webInterface.login(new TestLoginRequest("yutianran", "123456"))
                .compose(new TestWebTransformer<>(webLoading))
                .subscribe(new AbsWebSubscriber<TestLoginResponse>(webLoading) {
                    @Override
                    public void onSuccess(TestLoginResponse response) {
                        LogUtil.print("response=" + response.toString());
                        //断言
                        assertThat(response).isNotNull();
                        assertThat(response.getUid()).isNotEqualTo(0);
                        assertThat(response.getSession()).isNotNull();
                        //记录用户session标识
                        String session = response.getSession();
                        long userId = response.getUid();
                    }
                });
    }

}
