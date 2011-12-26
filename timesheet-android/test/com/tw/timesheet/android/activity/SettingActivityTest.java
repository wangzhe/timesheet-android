package com.tw.timesheet.android.activity;

import android.content.Intent;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.widget.SettingItemView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SettingActivityTest {

    private SettingActivity activity;
    private SettingItemView usernameView;
    private SettingItemView passwordView;
    private SettingItemView departmentView;
    private SettingItemView countryView;
    private CheckBox isOnBgServCheckBox;
    private ImageView addNotifButton;

    @Before
    public void setUp() throws Exception {
        activity = new SettingActivity();

        Intent newIntent = new Intent();
        newIntent.putExtra("statusData", new StatusData(new UserProfile("User Name"), null));
        activity.setIntent(newIntent);
        activity.onCreate(null);

        usernameView = (SettingItemView) activity.findViewById(R.id.setting_screen_username);
        passwordView = (SettingItemView) activity.findViewById(R.id.setting_screen_password);
        departmentView = (SettingItemView) activity.findViewById(R.id.setting_screen_department);
        countryView = (SettingItemView) activity.findViewById(R.id.setting_screen_country);
        isOnBgServCheckBox = (CheckBox) activity.findViewById(R.id.setting_screen_active_background_service);
        addNotifButton = (ImageView) activity.findViewById(R.id.setting_screen_add_notification);
    }

    @Test
    public void should_rendering_correct_UI() {
        TextView usernameTextView = (TextView) usernameView.findViewById(R.id.setting_item_edit_text);
        TextView passwordTextView = (TextView) passwordView.findViewById(R.id.setting_item_edit_text);
        ImageView deptImageView = (ImageView) departmentView.findViewById(R.id.setting_item_toggle_img);
        TextView countyTextView = (TextView) countryView.findViewById(R.id.setting_item_edit_text);

        assertThat(usernameTextView.getText().toString(), is("User Name"));
        assertThat(passwordTextView.getText().toString(), is(""));
    }
}
