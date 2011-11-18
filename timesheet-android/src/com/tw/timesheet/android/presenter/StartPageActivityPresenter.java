package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.LoginActivity;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.callback.StartPageActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;
import org.apache.http.impl.client.DefaultHttpClient;

public class StartPageActivityPresenter {

    private StartPageActivityView view;
    private DeviceSystem device;
    private UserResource userResource;

    public StartPageActivityPresenter(StartPageActivityView view, DeviceSystem device) {
        this.view = view;
        this.device = device;
    }

    public void startApp() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onAppStart(view.getFileRepository(UserProfile.class));
            }
        }.start();
    }

    protected void onAppStart(StorageRepository<UserProfile> storageRepository) {
        UserProfile userProfile = storageRepository.loadData(new UserProfile());
        view.startNextActivity(
                getNextActivityByUserProfileStatus(userProfile),
                new StatusData(userProfile.getUsername(), userResource));
        view.closeActivity();
    }

    private Class getNextActivityByUserProfileStatus(UserProfile userProfile) {
        Class nextActivity;
        userResource = userProfile.login(device.getActiveNetworkInfo(), DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient())));
        if (userResource == null) {
            nextActivity = LoginActivity.class;
        } else {
            nextActivity = MainActivity.class;
        }
        return nextActivity;
    }
}
