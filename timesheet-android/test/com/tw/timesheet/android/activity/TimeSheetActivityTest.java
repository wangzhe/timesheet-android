package com.tw.timesheet.android.activity;

import android.net.NetworkInfo;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TimeSheetActivityTest {

    private TimeSheetActivity timeSheetActivity;

    @Before
    public void setUp() {
        timeSheetActivity = new TimeSheetActivity();
    }

    @Test
    public void should_return_connective_manager() {
        System.out.println(timeSheetActivity.getActiveNetworkInfo().getClass());
        assertThat(timeSheetActivity.getActiveNetworkInfo().getClass(), is(NetworkInfo.class.getClass()));
    }
}
