package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.widget.Button;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;
import com.tw.timesheet.android.domain.StatusData;

public class SettingActivity extends TimeSheetActivity implements SettingActivityView {

    private Button saveButton;
    private Button cancelButton;
    private StatusData statusData;

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
        statusData = (StatusData) getIntent().getSerializableExtra("statusData");
    }
}
