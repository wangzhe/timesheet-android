package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;

public class TimeSheetListAdapterPresenter {

    private TimeSheetContentView viewer;
    private TimeSheetSummary summary;

    public TimeSheetListAdapterPresenter(TimeSheetContentView viewer, TimeSheetSummary summary) {
        this.viewer = viewer;
        this.summary = summary;
    }

    public ITimeSheetEntryView createTimeSheetEntryView(int index, ITimeSheetEntryView view) {
        ITimeSheetEntryView timeSheetEntryView;
        if (view == null) {
            timeSheetEntryView = viewer.createTimeSheetEntryView(summary.getEntry(index));
        } else {
            timeSheetEntryView = view;
            timeSheetEntryView.update(summary.getEntry(index));
        }
        return timeSheetEntryView;
    }

    public int getSummarySize() {
        return summary.size();
    }

    public TimeSheetEntry getSummaryEntry(int index) {
        return summary.getEntry(index);
    }
}
