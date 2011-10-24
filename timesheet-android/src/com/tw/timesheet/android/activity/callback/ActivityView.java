package com.tw.timesheet.android.activity.callback;

import com.tw.timesheet.android.storage.StorageRepository;

public interface ActivityView {

    void startNextActivity(Class activityClass, String username);

    void startNextActivity(Class activityClass);

    void closeActivity();

    StorageRepository getFileRepository(Class genericClassName);
}
