package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.background.TimeSheetContentTask;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.system.DeviceSystem;

public class TimeSheetContentPresenter {

    private StatusData statusData;
    private TimeSheetContentView viewer;
    private DeviceSystem device;
    private TimeSheetContentTask task;

    public TimeSheetContentPresenter(TimeSheetContentView viewer, DeviceSystem device) {
        this.viewer = viewer;
        this.device = device;
    }

    public void setStatusData(StatusData statusData) {
        this.statusData = statusData;
    }

    public void startFetchTimeSheetContent() {
        this.task = new TimeSheetContentTask(viewer, device, statusData);
        task.execute();
    }
}
