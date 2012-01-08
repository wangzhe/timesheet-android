package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;
import com.tw.timesheet.android.domain.Setting;
import com.tw.timesheet.android.domain.StatusData;

public class SettingActivityPresenter {

    private SettingActivityView viewer;
    private Setting setting;


    public SettingActivityPresenter(SettingActivityView viewer, StatusData statusData) {
        this.viewer = viewer;
        setting = statusData.getSetting();
    }

    public void deptClicked() {
        setting.switchDept();
        viewer.setDeptToggle(getDeptToggle());
    }

    public void init() {
        viewer.setDeptToggle(getDeptToggle());
    }

    private int getDeptToggle() {
        return (setting.isDeptPS()) ? R.drawable.dept_toggle_ps : R.drawable.dept_toggle_op;
    }

    public void addNotificationClicked() {
//        ScheduleNotification notification = setting.createNotification();
        System.out.println("viewer = " + "clicked");
        viewer.showWheelPopupWindow();
    }
}
