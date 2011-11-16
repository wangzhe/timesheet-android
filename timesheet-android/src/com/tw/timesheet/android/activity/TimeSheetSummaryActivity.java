package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.widget.ListView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.presenter.TimeSheetContentPresenter;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.widget.TimeSheetEntryView;

public class TimeSheetSummaryActivity extends TimeSheetActivity implements TimeSheetContentView, DeviceSystem {

    TimeSheetContentPresenter presenter;
    private ListView summaryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sheet_summary_screen);
        bindData();
        initUI();
        setListeners();
        presenter.startFetchTimeSheetContent();
    }

    private void bindData() {
        presenter.setStatusData((StatusData) getIntent().getSerializableExtra("statusData"));
    }

    private void initUI() {
        summaryListView = (ListView) findViewById(R.id.time_sheet_summary_screen_list);
        presenter = new TimeSheetContentPresenter(this, this);
    }

    private void setListeners() {

    }

    @Override
    public void appendTimeSheetEntry(TimeSheetEntryView entryView) {
    }

    @Override
    public TimeSheetEntryView createTimeSheetEntryView(TimeSheetEntry entry) {
        return new TimeSheetEntryView(getApplication().getApplicationContext(), entry);
    }
}
