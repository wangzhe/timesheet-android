package com.tw.timesheet.android.activity.callback;

import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.storage.StorageRepository;

public interface ActivityView {

    void startNextActivity(Class activityClass, StatusData statusData);

    void startNextActivity(Class activityClass);

    void closeActivity();

    StorageRepository getFileRepository(Class genericClassName);
}
