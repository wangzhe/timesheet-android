package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.callback.LoginActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;
import org.apache.http.impl.client.DefaultHttpClient;

public class LoginActivityPresenter {
    private LoginActivityView viewer;
    private DeviceSystem device;

    public LoginActivityPresenter(LoginActivityView viewer, DeviceSystem device) {
        this.viewer = viewer;
        this.device = device;
    }

    public void reset() {
        viewer.setUsernameEditText("");
        viewer.setPasswordEditText("");
    }

    public void updateUserProfile(UserProfile userProfile) {
        userProfile.update(viewer.getUsername(), viewer.getPassword());
    }

    public void login(UserProfile userProfile) {
        updateUserProfile(userProfile);
        UserResource userResource = userProfile.login(device.getActiveNetworkInfo(), DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient())));
        if (userResource == null) {
            viewer.setStatusText("user or password is invalid");
        } else {
            StorageRepository fileRepository = viewer.getFileRepository(UserProfile.class);
            fileRepository.saveData(userProfile);
            viewer.startNextActivity(MainActivity.class, new StatusData(userProfile.getUsername(), userResource));
            viewer.closeActivity();
        }
    }

}
