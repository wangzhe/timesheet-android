package com.tw.timesheet.android.activity;

import android.os.Bundle;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.system.DeviceSystem;

public class TimeSheetDetailActivity extends TimeSheetActivity implements DeviceSystem {

//    TimeSheetContentPresenter presenter = new TimeSheetContentPresenter(this, this);

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
//        presenter.setStatusData((StatusData) getIntent().getSerializableExtra("statusData"));
    }

    private void initUI() {

    }
}
