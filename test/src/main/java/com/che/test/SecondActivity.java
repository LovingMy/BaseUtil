package com.che.test;

import android.app.Activity;
import android.os.Bundle;

import com.che.fast_ioc.InjectUtil;

/**
 * 作者：余天然 on 16/9/15 下午3:15
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InjectUtil.bind(this);
    }

    @Override
    protected void onDestroy() {
        InjectUtil.unbind(this);
        super.onDestroy();
    }
}
