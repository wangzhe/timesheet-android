package com.tw.timesheet.android.activity.callback;

import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.widget.TimeSheetEntryView;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;

public interface TimeSheetContentView {
    
    void appendTimeSheetSummary(TimeSheetSummary summary);

    ITimeSheetEntryView createTimeSheetEntryView(TimeSheetEntry entry);

    
}
