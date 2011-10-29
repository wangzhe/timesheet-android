package com.tw.timesheet.android.activity;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class StartPageActivityTest {

    @Test  //TODO not sure how to do thread test
    public void should_start_on_app() {
        StartPageActivity startPageActivity = new StartPageActivity();
        startPageActivity.onCreate(null);
    }
}
