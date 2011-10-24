package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;

public class SettingActivity extends Activity implements SettingActivityView {

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
