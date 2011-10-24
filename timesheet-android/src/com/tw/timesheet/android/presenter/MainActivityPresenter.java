package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.callback.MainActivityView;

public class MainActivityPresenter {

    private MainActivityView view;
    private String username;

    public MainActivityPresenter(MainActivityView view) {
        this.view = view;
    }

    public void addTimeSheetButtonClicked() {
        //I think here the UT should just test whether the text had been set
        view.setTitleText("Go to add timesheet");
    }

    public void viewTimeSheetButtonClicked() {
        //I think here the UT should just test whether the text had been set
        view.setTitleText("Go to edit timesheet");
    }

    public void settingButtonClicked() {
        //I think here the UT should just test whether the text had been set
        view.startNextActivity(SettingActivity.class);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void initUI() {
        view.setTitleText("Hello " + username + ", this is Main View");
    }
}
