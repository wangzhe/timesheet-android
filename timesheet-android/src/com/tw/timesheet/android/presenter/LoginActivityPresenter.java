package com.tw.timesheet.android.presenter;

import android.view.View;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.callback.LoginActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.system.DeviceSystem;
import org.apache.http.impl.client.DefaultHttpClient;

public class LoginActivityPresenter {
    private LoginActivityView viewer;
    private DeviceSystem device;

    public LoginActivityPresenter(LoginActivityView viewer, DeviceSystem device) {
        this.viewer = viewer;
        this.device = device;
    }

    public void login(UserProfile userProfile) {
        UserResource userResource = userProfile.login(device.getActiveNetworkInfo(), DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient())));
        if (userResource == null) {
            viewer.setStatusText("user or password is invalid");
        } else {
            viewer.startNextActivity(MainActivity.class, new StatusData(userProfile.getUsername(), userResource));
        }
    }

    public void reset() {
        viewer.setUsernameEditText("");
        viewer.setPasswordEditText("");
    }

    public void setListeners(final UserProfile userProfile) {
        viewer.setLoginButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(userProfile);
            }
        });
        viewer.setResetButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

}
