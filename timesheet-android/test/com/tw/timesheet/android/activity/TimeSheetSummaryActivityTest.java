package com.tw.timesheet.android.activity;

import android.content.Intent;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserResource;
import org.junit.Before;

public class TimeSheetSummaryActivityTest {

    private TimeSheetSummaryActivity activity;

    @Before
    public void setUp() {
        activity = new TimeSheetSummaryActivity();
        Intent newIntent = new Intent();
        StatusData statusData = new StatusData("UserA", new UserResource("userA/"));
        newIntent.putExtra("statusData", statusData);
        activity.setIntent(newIntent);

//        new TimeSheetContentPresenter(activity, xxx);
    }
}
