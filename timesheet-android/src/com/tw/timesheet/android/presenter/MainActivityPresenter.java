package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.TimeSheetDetailActivity;
import com.tw.timesheet.android.activity.TimeSheetSummaryActivity;
import com.tw.timesheet.android.activity.callback.MainActivityView;
import com.tw.timesheet.android.domain.StatusData;

public class MainActivityPresenter {

    private MainActivityView viewer;
    private StatusData statusData;

    public MainActivityPresenter(MainActivityView view) {
        this.viewer = view;
    }

    public void addTimeSheetButtonClicked() {
        //I think here the UT should just test whether the text had been set
        viewer.setTitleText("Go to add timesheet");
        viewer.startNextActivity(TimeSheetDetailActivity.class, statusData);
    }

    public void viewTimeSheetButtonClicked() {
        //I think here the UT should just test whether the text had been set
        viewer.setTitleText("Go to edit timesheet");
        viewer.startNextActivity(TimeSheetSummaryActivity.class, statusData);
    }

    public void settingButtonClicked() {
        //I think here the UT should just test whether the text had been set
        viewer.startNextActivity(SettingActivity.class, statusData);
    }

    public void setStatusData(StatusData statusData) {
        this.statusData = statusData;
    }

    public void initUI() {
        viewer.setTitleText("Hello " + statusData.getUsername() + ", this is Main View");
    }
}
