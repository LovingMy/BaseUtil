package com.che.test.timer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.che.base_util.LogUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 计时服务
 * <p>
 * 作者：余天然 on 16/9/19 下午1:11
 */
public class TimerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.print("");
        //初始化
        this.init();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.print("");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.print("");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.print("");
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //发送事件:Thread=
            sendUpdateTimeEvent();
            handler.postDelayed(this, 1000);
        }

    };

    /**
     * 初始化
     */
    private void init() {
        runnable.run();
    }

    /**
     * 发送广播，通知UI层时间已改变
     */
    private void sendUpdateTimeEvent() {
        long time = System.currentTimeMillis();
        LogUtil.print("发送事件：time=" + time);
        EventBus.getDefault().post(new UpdateTimeEvent(time));
    }

}
