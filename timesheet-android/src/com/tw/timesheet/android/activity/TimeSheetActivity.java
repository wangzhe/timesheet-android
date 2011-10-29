package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tw.timesheet.android.activity.callback.ActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.storage.FileRepository;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;

public class TimeSheetActivity extends Activity implements ActivityView, DeviceSystem {

    @Override
    public void startNextActivity(Class activityClass, StatusData statusData) {
        Intent intent = new Intent();
        intent.setClass(this, activityClass);
        intent.putExtra("statusData", statusData);
        startActivity(intent);
    }

    @Override
    public void startNextActivity(Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(this, activityClass);
        startActivity(intent);
    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public StorageRepository getFileRepository(Class genericClassName) {
        return new FileRepository(getApplicationContext());
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
