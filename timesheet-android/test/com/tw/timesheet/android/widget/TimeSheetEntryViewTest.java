package com.tw.timesheet.android.widget;

import android.app.Activity;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(RobolectricTestRunner.class)
public class TimeSheetEntryViewTest {

    private TimeSheetEntryView timeSheetEntryView;
    private TextView weekEndingDate;
    private TextView action;
    private TextView billableValue;
    private TextView nonBillableValue;

    @Before
    public void setUp() {
        TimeSheetEntry entry = new TimeSheetEntry("06-Nov-2011", "07-Nov-2011 @ 19:19:50", "40.00", "6.00", "46.00", "submitted", "07-Nov-2011 @ 19:19:50");
        timeSheetEntryView = new TimeSheetEntryView(new Activity(), entry);
    }

    @Test
    public void should_show_the_views() {
        weekEndingDate = (TextView) timeSheetEntryView.findViewById(R.id.time_sheet_entry_view_week_ending_date);
        action = (TextView) timeSheetEntryView.findViewById(R.id.time_sheet_entry_view_action);
        billableValue = (TextView) timeSheetEntryView.findViewById(R.id.time_sheet_entry_view_billable_value);
        nonBillableValue = (TextView) timeSheetEntryView.findViewById(R.id.time_sheet_entry_view_non_billable_value);

        assertThat(weekEndingDate.getText().toString(), is("06-Nov-2011"));
        assertThat(action.getText().toString(), is("submitted"));
        assertThat(billableValue.getText().toString(), is("40.00"));
        assertThat(nonBillableValue.getText().toString(), is("6.00"));
    }
}
