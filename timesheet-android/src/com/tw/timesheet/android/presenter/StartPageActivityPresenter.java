package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.LoginActivity;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.callback.StartPageActivityView;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;

public class StartPageActivityPresenter {

    private StartPageActivityView view;
    private DeviceSystem device;

    public StartPageActivityPresenter(StartPageActivityView view, DeviceSystem device) {
        this.view = view;
        this.device = device;
    }

    public void startApp() {
        new Thread() {
            public void run() {
                onAppStart(view.getFileRepository(UserProfile.class));
            }
        }.start();
    }

    protected void onAppStart(StorageRepository<UserProfile> storageRepository) {
        UserProfile userProfile = storageRepository.loadData(new UserProfile());
        view.startNextActivity(
                getNextActivityByUserProfileStatus(userProfile),
                userProfile.getUsername());
        view.closeActivity();
    }

    private Class getNextActivityByUserProfileStatus(UserProfile userProfile) {
        Class nextActivity;
        if (!userProfile.login(device.getActiveNetworkInfo(), null)) {
            nextActivity = LoginActivity.class;
        } else {
            nextActivity = (!userProfile.hasDefaultSetting()) ? SettingActivity.class : MainActivity.class;
        }
        return nextActivity;
    }
}
