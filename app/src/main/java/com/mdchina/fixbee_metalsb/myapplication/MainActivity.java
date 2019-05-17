package com.mdchina.fixbee_metalsb.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private boolean firstload = true;
    @BindView(R.id.btn_test)
    Button button;
    @BindView(R.id.tv_date)
    TextView tv_dates;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initview();
    }

    /**
     * 根据以下要求完成一个简单的Android应用：
     * 1. 应用启动时记录启动日期
     * 2. UI上显示一个按钮和一个文本框
     * 3. 按钮按下时，根据当前时间做以下判断：
     * - 如果应用第一次打开，在文本框显示消息“欢迎初次使用”
     * - 如果当前时间距上次打开时间小于三天，在文本框显示消息“欢迎经常使用”
     * - 如果当前时间距上次打开时间大于三天，在文本框显示消息“好久不见，欢迎再次使用”
     * 4. 写出相应的test case测试以上逻辑
     */
    private void initview() {
        firstload = PreferencesUtils.getBoolean(context, "IsFirst", true);
        //判断是否第一次登录
        if (firstload) {
            PreferencesUtils.putBoolean(context, "IsFirst", false);
            //第一次登录获取时间
            long currentTime = System.currentTimeMillis();
            PreferencesUtils.putLong(context, "currrentTime", currentTime);

            tv_dates.setText("欢迎初次使用");
        } else {
            //非第一次登录


            
        }

    }

    @OnClick({R.id.btn_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                long btn_current = System.currentTimeMillis();
                //三天后
                long oldcurrent = PreferencesUtils.getLong(context, "currrentTime") + 1000 * 60 * 60 * 24 * 3;
                if (btn_current >= oldcurrent) {
                    tv_dates.setText("好久不见，欢迎再次使用");
                } else {
                    tv_dates.setText("欢迎经常使用");
                }
                break;
        }
    }


}
