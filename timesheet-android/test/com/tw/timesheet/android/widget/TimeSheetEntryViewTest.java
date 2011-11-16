package com.tw.timesheet.android.widget;

import com.tw.timesheet.android.domain.TimeSheetEntry;
import org.junit.Before;
import org.junit.Test;

public class TimeSheetEntryViewTest {

    private TimeSheetEntryView timeSheetEntryView;

    @Before
    public void setUp() {
        TimeSheetEntry entry = new TimeSheetEntry("06-Nov-2011", "07-Nov-2011 @ 19:19:50", "40.00", "6.00", "46.00", "submitted", "07-Nov-2011 @ 19:19:50");
        timeSheetEntryView = new TimeSheetEntryView(null, entry);
    }

    @Test
    public void should_show_the_views() {
        
    }
}
