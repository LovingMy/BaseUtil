package com.che.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.che.test.config.IntentAction;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.tv_timer)
    Button tvTimer;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        random = new Random();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @OnClick({R.id.tv, R.id.bt, R.id.tv_timer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                bt.setText(random.nextInt(100) + "");
                break;
            case R.id.bt:
                tv.setText("我要变");
                Intent intent = new Intent();
                intent.setAction(IntentAction.ACTIVITY_SECOND);
                startActivity(intent);
                break;
            case R.id.tv_timer:
                Intent intent2 = new Intent();
                intent2.setAction(IntentAction.ACTIVITY_TIMER);
                startActivity(intent2);
                break;
        }

    }

}
