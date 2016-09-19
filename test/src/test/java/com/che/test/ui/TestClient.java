package com.che.test.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.che.base_util.DensityUtil;
import com.che.base_util.FileUtil;
import com.che.base_util.LogUtil;
import com.che.base_util.MD5Util;
import com.che.base_util.NetStateUtil;
import com.che.base_util.SPUtil;
import com.che.base_util.VersionUtil;
import com.che.test.BuildConfig;
import com.che.test.MainActivity;
import com.che.test.R;
import com.che.test.ui.bean.SPObj;
import com.che.test.config.IntentAction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import static com.google.common.truth.Truth.assertThat;

/**
 * 作者：余天然 on 16/9/15 下午2:08
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "src/main/AndroidManifest.xml",
        packageName = "com.che.baseutil",
        resourceDir = "res")
public class TestClient {

    private Context context;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        context = RuntimeEnvironment.application;
    }

    @Test
    public void testLogUtil() {
        LogUtil.print("你好");
        LogUtil.print(Log.ERROR, "你好");
        LogUtil.print(Log.ERROR, "test", "你好");

        LogUtil.setIsDebug(false);
        LogUtil.print("关闭打印");
    }

    @Test
    public void testSPUtil() {
        SPUtil.init(context);

        LogUtil.print("存储-String:" + SPUtil.putValue("String", "哈哈"));
        LogUtil.print("存储-int:" + SPUtil.putValue("int", 1));
        LogUtil.print("存储-long:" + SPUtil.putValue("long", 2l));
        LogUtil.print("存储-float:" + SPUtil.putValue("float", 3f));
        LogUtil.print("存储-boolean:" + SPUtil.putValue("boolean", true));

        LogUtil.print("读取-String:" + SPUtil.getValue("String", ""));
        LogUtil.print("读取-int:" + SPUtil.getValue("int", 0));
        LogUtil.print("读取-long:" + SPUtil.getValue("long", 0l));
        LogUtil.print("读取-float:" + SPUtil.getValue("float", 0f));
        LogUtil.print("读取-boolean:" + SPUtil.getValue("boolean", false));

        SPObj putObj = new SPObj("李白", 32);
        LogUtil.print("存储-Object:" + SPUtil.putObject("SPObj", putObj));

        SPObj getObj = SPUtil.getObject("SPObj", SPObj.class);
        LogUtil.print("读取-Object:" + getObj.toString());
    }

    @Test
    public void testVersionUtil() {
        VersionUtil.init(context);

        LogUtil.print("versionCode=" + VersionUtil.getVersionCode());
        LogUtil.print("versionName=" + VersionUtil.getVersionName());
    }

    @Test
    public void testNetStateUtil() {
        NetStateUtil.init(context);

        NetStateUtil.NetState netState = NetStateUtil.getNetState();
        LogUtil.print("netState=" + netState);
    }

    @Test
    public void testMD5Util() {
        String encode = MD5Util.encode("123456");
        LogUtil.print("encode=" + encode);
        assertThat(encode).isEqualTo("e10adc3949ba59abbe56e057f20f883e");
    }

    @Test
    public void testDensityUtil() {
        DensityUtil.init(context);

        LogUtil.print("screenWidth=" + DensityUtil.getScreenWidth());
        LogUtil.print("screenHeight=" + DensityUtil.getScreenHeight());

        LogUtil.print("dp2px:" + DensityUtil.dp2px(10));
        LogUtil.print("sp2px:" + DensityUtil.sp2px(10));
        LogUtil.print("px2dp:" + DensityUtil.px2dp(10));
        LogUtil.print("px2sp:" + DensityUtil.px2sp(10));
    }

    @Test
    public void testFileUtil() {
        String assets = FileUtil.getFromAssets(context, "a.txt");
        LogUtil.print("assets=" + assets);
        assertThat(assets).isEqualTo("我是从assets读取的");
    }

    @Test
    public void testEnvironment() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Assert.assertNotNull(absolutePath);
        LogUtil.print("absolutePath: " + absolutePath);
    }

    @Test
    public void testPermissions() {
        String MERGED_MANIFEST = "build/intermediates/manifests/full/debug/AndroidManifest.xml";
        String EXPECTED_PERMISSIONS = "android.permission.INTERNET";

        AndroidManifest manifest = new AndroidManifest(Fs.fileFromPath(MERGED_MANIFEST), null, null);
        LogUtil.print(manifest.getUsedPermissions().toString());
        assertThat(manifest.getUsedPermissions()).contains(EXPECTED_PERMISSIONS);
    }

    @Test
    public void testToast() {
        Toast.makeText(context, "你好呀", Toast.LENGTH_SHORT).show();
        String toastMsg = ShadowToast.getTextOfLatestToast();
        LogUtil.print("toastMsg=" + toastMsg);
        assertThat(toastMsg).isEqualTo("你好呀");
    }

    @Test
    public void testActivity() {
        //加载Activity
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        //加载控件
        TextView tv = (TextView) activity.findViewById(R.id.tv);
        Button bt = (Button) activity.findViewById(R.id.bt);
        //执行点击
        bt.performClick();
        //判断TextView是否改变
        String text = tv.getText().toString();
        LogUtil.print("tv.text=" + text);
        assertThat(text).isEqualTo("我要变");
        //判断Activity是否跳转
        Intent actualIntent = Shadows.shadowOf(activity).getNextStartedActivity();
        Intent expectedIntent = new Intent();
        expectedIntent.setAction(IntentAction.ACTIVITY_SECOND);
        LogUtil.print("actualIntent.action=" + actualIntent.getAction());
        assertThat(expectedIntent.getAction()).isEqualTo(actualIntent.getAction());
    }

    @Test
    public void testResources() {
        String string = context.getResources().getString(R.string.tv_text);
        LogUtil.print("string=" + string);
        assertThat(string).isEqualTo("我是文本框");

        float dimension = context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        LogUtil.print("dimension=" + dimension);
        assertThat(dimension).isEqualTo(16.0f);

        int color = context.getResources().getColor(R.color.colorAccent);
        LogUtil.print("color=" + color);
        assertThat(color).isEqualTo(0xffFF4081);
    }

    @Test
    public void test() {

    }
}
