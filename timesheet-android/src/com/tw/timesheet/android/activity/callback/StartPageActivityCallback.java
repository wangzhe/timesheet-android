package com.tw.timesheet.android.activity.callback;

public interface StartPageActivityCallback extends ActivityCallback{

    void startNextActivity(Class activityClass, String username);
}
