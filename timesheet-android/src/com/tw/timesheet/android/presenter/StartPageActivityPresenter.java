package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.LoginActivity;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.SettingActivity;
import com.tw.timesheet.android.activity.callback.StartPageActivityView;
import com.tw.timesheet.android.domain.NetworkResource;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;
import org.apache.http.impl.client.DefaultHttpClient;

public class StartPageActivityPresenter {

    private StartPageActivityView view;
    private DeviceSystem device;
    private NetworkResource networkResource;

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
        networkResource = userProfile.login(device.getActiveNetworkInfo(), DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient())));
        if (networkResource == null) {
            nextActivity = LoginActivity.class;
        } else {
            nextActivity = (userProfile.hasDefaultSetting()) ? MainActivity.class : SettingActivity.class;
        }
        return nextActivity;
    }
}
