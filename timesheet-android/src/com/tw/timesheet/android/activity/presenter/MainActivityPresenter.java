package com.tw.timesheet.android.activity.presenter;

import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.callback.MainActivityCallback;

public class MainActivityPresenter {

    private MainActivityCallback callback;
    private String username;

    public MainActivityPresenter(MainActivityCallback callback) {
        this.callback = callback;
    }

    public void clickAddTimesheetButton() {
        //I think here the UT should just test whether the text had been set
        callback.setTitleText("Go to add timesheet");
    }

    public void clickViewTimesheetButton() {
        //I think here the UT should just test whether the text had been set
        callback.setTitleText("Go to edit timesheet");
    }

    public void clickSettingButton() {
        //I think here the UT should just test whether the text had been set
        callback.startNextActivity(SettingActivity.class);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void initUI() {
        callback.setTitleText("Hello " + username + ", this is Main View");
    }
}
