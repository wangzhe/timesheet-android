package com.tw.timesheet.android.activity.callback;

import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.widget.TimeSheetEntryView;

public interface TimeSheetContentView {
    
    void appendTimeSheetEntry(TimeSheetEntryView entryView);

    TimeSheetEntryView createTimeSheetEntryView(TimeSheetEntry entry);
}
