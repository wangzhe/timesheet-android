package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;
import com.tw.timesheet.android.domain.Constant;
import com.tw.timesheet.android.domain.Setting;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.storage.StorageRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SettingActivityPresenterTest {

    private SettingActivityPresenter presenter;
    private SettingActivityPresenterTest.MockSettingActivityView viewer;

    @Before
    public void setUp() {
        viewer = new MockSettingActivityView();
    }

    @Test
    public void should_show_ps_toggle_in_view_screen_when_init_with_related_setting() {
        Setting setting = new Setting(Constant.DEPT_PS, "China");
        presenter = new SettingActivityPresenter(viewer, new StatusData(new UserProfile("", ""), null));
        presenter.init();
        assertThat(viewer.getDeptToggleResId(), is(R.drawable.dept_toggle_ps));
    }

    @Test
    public void should_show_op_toggle_in_view_screen_when_init_with_related_setting() {
        Setting setting = new Setting(Constant.DEPT_OP, "China");
        presenter = new SettingActivityPresenter(viewer, new StatusData(new UserProfile(setting), null));
        presenter.init();
        assertThat(viewer.getDeptToggleResId(), is(R.drawable.dept_toggle_op));
    }

    @Test
    public void should_show_op_toggle_in_view_screen_when_clicked_in_ps_toggle() {
        Setting setting = new Setting(Constant.DEPT_OP, "China");
        presenter = new SettingActivityPresenter(viewer, new StatusData(new UserProfile(setting), null));
        presenter.deptClicked();
        assertThat(viewer.getDeptToggleResId(), is(R.drawable.dept_toggle_ps));
    }

    @Test
    public void should_show_a_new_notification_and_setting_wheels_when_add_notification_clicked() {
        //show a new notification
        Setting setting = new Setting(Constant.DEPT_OP, "China");
        presenter = new SettingActivityPresenter(viewer, new StatusData(new UserProfile(setting), null));
        presenter.addNotificationClicked();

        assertThat(setting.getScheduleList().size(), is(1));
        assertThat(viewer.getNotificationChildren().size(), is(1));
        assertThat(viewer.isTimeConfigurationDialogShown(), is(true));
    }

    private class MockSettingActivityView implements SettingActivityView {
        private int resId;
        private List notifications;
        private boolean dialogShown;

        public int getDeptToggleResId() {
            return resId;
        }

        @Override
        public void setDeptToggle(int resId) {
            this.resId = resId;
        }

        @Override
        public void showWheelPopupWindow() {
            dialogShown = true;
        }

        @Override
        public void startNextActivity(Class activityClass, StatusData statusData) {
        }

        @Override
        public void startNextActivity(Class activityClass) {
        }

        @Override
        public void closeActivity() {
        }

        @Override
        public StorageRepository getFileRepository(Class genericClassName) {
            return null;
        }

        public List getNotificationChildren() {
            return notifications;
        }

        public boolean isTimeConfigurationDialogShown() {
            return dialogShown;
        }
    }
}
