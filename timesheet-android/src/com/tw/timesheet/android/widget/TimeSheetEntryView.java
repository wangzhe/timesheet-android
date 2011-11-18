package com.tw.timesheet.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;

public class TimeSheetEntryView extends RelativeLayout implements ITimeSheetEntryView{

    private TextView weekEndingDate;
    private TextView action;
    private TextView billableValue;
    private TextView nonBillableValue;

    public TimeSheetEntryView(Context context, TimeSheetEntry entry) {
        super(context);
        initUI();
        bindDate(entry);
        setListeners();
        
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.time_sheet_entry_view, this, false);
        this.addView(content);

        weekEndingDate = (TextView) findViewById(R.id.time_sheet_entry_view_week_ending_date);
        action = (TextView) findViewById(R.id.time_sheet_entry_view_action);
        billableValue = (TextView) findViewById(R.id.time_sheet_entry_view_billable_value);
        nonBillableValue = (TextView) findViewById(R.id.time_sheet_entry_view_non_billable_value);
    }

    private void bindDate(TimeSheetEntry entry) {
        update(entry);
    }

    @Override
    public void update(TimeSheetEntry entry) {
        weekEndingDate.setText(entry.getWeekEndDate());
        action.setText(entry.getStatus());
        billableValue.setText(entry.getBillableHours());
        nonBillableValue.setText(entry.getNonBillableHours());
    }

    private void setListeners() {
    }
}
