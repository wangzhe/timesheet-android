package com.tw.timesheet.android.activity.callback;

public interface ActivityCallback {

    void startNextActivity(Class activityClass);

    void closeActivity();
}
