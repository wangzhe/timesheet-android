package com.tw.timesheet.android.activity.presenter;

import com.tw.timesheet.android.activity.LoginActivity;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.callback.StartPageActivityCallback;
import com.tw.timesheet.android.domain.UserProfile;

public class StartPageActivityPresenter {

    private StartPageActivityCallback callback;
    private UserProfile userProfile = null;

    public StartPageActivityPresenter(StartPageActivityCallback callback) {
        this.callback = callback;
    }

    public void startApp() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    onAppStart();
                    callback.closeActivity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //TODO should test this method in all paths
    private void onAppStart() {
        userProfile = UserProfile.getUserProfile();
        String username = userProfile.getUsername();
        if (!userProfile.isLoginSuccessful()) {
            callback.startNextActivity(LoginActivity.class, username);
            return;
        }
        if (!userProfile.hasDefaultSetting()) {
            callback.startNextActivity(SettingActivity.class, username);
        } else {
            callback.startNextActivity(MainActivity.class, username);
        }
    }
}
