package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.presenter.TimeSheetContentPresenter;
import com.tw.timesheet.android.presenter.TimeSheetListAdapterPresenter;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.widget.TimeSheetEntryView;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;

public class TimeSheetSummaryActivity extends TimeSheetActivity implements TimeSheetContentView, DeviceSystem {

    TimeSheetContentPresenter presenter;
    private ListView summaryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sheet_summary_screen);
        initUI();
        bindData();
        setListeners();
        presenter.startFetchTimeSheetContent();
    }

    private void initUI() {
        summaryListView = (ListView) findViewById(R.id.time_sheet_summary_screen_list);
        presenter = new TimeSheetContentPresenter(this, this);
    }

    private void bindData() {
        presenter.setStatusData((StatusData) getIntent().getSerializableExtra("statusData"));
    }

    private void setListeners() {

    }

    @Override
    public void appendTimeSheetSummary(TimeSheetSummary summary) {
        summaryListView.setAdapter(new TWListAdapter(summary));
    }

    @Override
    public ITimeSheetEntryView createTimeSheetEntryView(TimeSheetEntry entry) {
        return new TimeSheetEntryView(getApplication().getApplicationContext(), entry);
    }

    private class TWListAdapter extends BaseAdapter {

        private TimeSheetListAdapterPresenter presenter;

        private TWListAdapter(TimeSheetSummary summary) {
            presenter = new TimeSheetListAdapterPresenter(TimeSheetSummaryActivity.this, summary);
        }

        @Override
        public int getCount() {
            return presenter.getSummarySize();
        }

        @Override
        public Object getItem(int index) {
            return presenter.getSummaryEntry(index);
        }

        @Override
        public View getView(int index, View view, ViewGroup viewGroup) {
            return (View) presenter.createTimeSheetEntryView(index, (ITimeSheetEntryView)view);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

    }
}
