package com.tw.timesheet.android.activity;

import android.os.Bundle;
import com.tw.timesheet.android.R;

public class TimeSheetDetailActivity extends TimeSheetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sheet_detail_screen);
        bindData();
        initUI();
        setListeners();
    }

    private void setListeners() {

    }

    private void bindData() {

    }

    private void initUI() {

    }
}
