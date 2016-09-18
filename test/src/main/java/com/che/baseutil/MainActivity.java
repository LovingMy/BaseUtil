package com.che.baseutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.che.fast_ioc.InjectUtil;
import com.che.fast_ioc.annotation.IClick;
import com.che.fast_ioc.annotation.IColor;
import com.che.fast_ioc.annotation.IContentView;
import com.che.fast_ioc.annotation.IString;
import com.che.fast_ioc.annotation.IView;

import java.util.Random;


@IContentView(R.layout.activity_main)
public class MainActivity extends Activity {
    @IView(R.id.tv)
    TextView tv;
    @IView(R.id.bt)
    Button bt;
    @IString(R.string.text_home)
    String title;
    @IColor(R.color.turquoise)
    int turquoiseColor;
    @IColor(R.color.moccasin)
    int moccasinColor;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.bind(this);
        random = new Random();
        tv.setText(title);
        tv.setBackgroundColor(turquoiseColor);
        bt.setBackgroundColor(moccasinColor);
    }

    @Override
    protected void onDestroy() {
        InjectUtil.unbind(this);
        super.onDestroy();
    }

    @IClick({R.id.tv, R.id.bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                bt.setText(random.nextInt(100) + "");
                break;
            case R.id.bt:
                tv.setText("我要变");
                Intent intent = new Intent();
                intent.setAction(IntentKey.ACTIVITY_SECOND);
                startActivity(intent);
                break;
        }

    }
}
