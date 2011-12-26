package com.tw.timesheet.android.activity;

import android.content.Intent;
import android.widget.Button;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.presenter.MainActivityPresenter;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private Button insertTimeSheetButton;
    private Button viewTimeSheetButton;
    private Button settingButton;
    private MainActivityPresenter presenter;
    private StatusData statusData;

    @Before
    public void setUp() throws Exception {
        mainActivity = new MainActivity();
        presenter = new MainActivityPresenter(mainActivity);
        statusData = new StatusData(new UserProfile("userA"), null);
        Intent newIntent = new Intent();
        newIntent.putExtra("statusData", statusData);
        mainActivity.setIntent(newIntent);
        mainActivity.onCreate(null);
        presenter.setStatusData(statusData);


        insertTimeSheetButton = (Button) mainActivity.findViewById(R.id.main_screen_add_time_sheet_button);
        viewTimeSheetButton = (Button) mainActivity.findViewById(R.id.main_screen_edit_time_sheet_button);
        settingButton = (Button) mainActivity.findViewById(R.id.bt_setting);
    }

    @Test
    public void should_go_to_setting_activity_when_setting_button_clicked() {
        settingButton.performClick();

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(SettingActivity.class.getName()));
    }

    @Test
    public void should_go_to_time_sheet_detail_activity_when_add_time_sheet_button_clicked() {
        insertTimeSheetButton.performClick();

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(TimeSheetDetailActivity.class.getName()));
    }

    @Test
    public void should_go_to_time_sheet_summary_activity_when_view_time_sheet_button_clicked() {
        viewTimeSheetButton.performClick();

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(TimeSheetSummaryActivity.class.getName()));
    }
}
