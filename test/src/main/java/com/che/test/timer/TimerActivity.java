package com.che.test.timer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.che.base_util.LogUtil;
import com.che.test.R;
import com.che.test.config.IntentAction;
import com.che.fast_ioc.InjectUtil;
import com.che.fast_ioc.annotation.IContentView;
import com.che.fast_ioc.annotation.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;

/**
 * 作者：余天然 on 16/9/19 下午1:05
 */
@IContentView(R.layout.activity_timer)
public class TimerActivity extends Activity {

    @IView(R.id.tv_time)
    TextView tvTime;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.bind(this);
        EventBus.getDefault().register(this);
        init();
    }

    @Override
    protected void onDestroy() {
        InjectUtil.unbind(this);
        EventBus.getDefault().unregister(this);
        stopTimerService();
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object object) {
        if (object instanceof UpdateTimeEvent) {
            UpdateTimeEvent event = (UpdateTimeEvent) object;
            long time = ((UpdateTimeEvent) object).getTime();
            LogUtil.print("接受事件：time=" + time);
            tvTime.setText(dateFormat.format(time));
        }
    }

    /**
     * 初始化
     */
    private void init() {
        tvTime.setTextColor(Color.RED);
        tvTime.setTextSize(15);
        startTimerService();
    }

    /**
     * 启动服务
     */
    private void startTimerService() {
        Intent intent = new Intent();
        intent.setAction(IntentAction.SERVICE_TIMER);
        intent.setPackage(this.getPackageName());
        startService(intent);
    }

    /**
     * 关闭服务
     */
    private void stopTimerService() {
        Intent intent = new Intent();
        intent.setAction(IntentAction.SERVICE_TIMER);
        intent.setPackage(this.getPackageName());
        stopService(intent);
    }
}
