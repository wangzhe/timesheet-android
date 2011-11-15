package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.widget.TimeSheetEntryView;

public class TimeSheetContentWidgetPresenter {


    private TimeSheetContentView viewer;

    public TimeSheetContentWidgetPresenter(TimeSheetContentView viewer) {
        this.viewer = viewer;
    }

    public TimeSheetEntryView composeTimeSheetEntryView(TimeSheetEntry entry) {
        return viewer.createTimeSheetEntryView(entry);
    }
}
