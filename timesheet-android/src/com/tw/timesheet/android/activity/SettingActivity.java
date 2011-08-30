package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityCallback;

public class SettingActivity extends Activity implements SettingActivityCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_screen);
        bindDate();
        initUI();
    }

    private void initUI() {

    }

    private void bindDate() {

    }
}
