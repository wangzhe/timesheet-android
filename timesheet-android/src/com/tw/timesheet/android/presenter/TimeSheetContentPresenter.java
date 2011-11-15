package com.tw.timesheet.android.presenter;

import android.net.NetworkInfo;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.background.TimeSheetContentTask;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.TimeSheetService;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.widget.TimeSheetEntryView;
import org.apache.http.impl.client.DefaultHttpClient;

public class TimeSheetContentPresenter {

    private StatusData statusData;
    private TimeSheetContentView viewer;
    private DeviceSystem device;
    private TimeSheetContentTask task;
    private TimeSheetContentWidgetPresenter widgetPresenter;

    public TimeSheetContentPresenter(TimeSheetContentView viewer, DeviceSystem device) {
        this.viewer = viewer;
        this.device = device;
        this.task = new TimeSheetContentTask(this);
    }

    public void setStatusData(StatusData statusData) {
        this.statusData = statusData;
    }

    public void startFetchTimeSheetContent() {
        task.execute();
    }

    public TimeSheetSummary fetchTimeSheetContent() {
        NetworkInfo networkInfo = device.getActiveNetworkInfo();
        DataServer dataServer = DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient()));
        UserResource userResource = statusData.getUserResource();
        return new TimeSheetService().fetch(networkInfo, dataServer, userResource);
    }

    public void updateTimeSheetContent(TimeSheetSummary summary) {
        for (int i = 0; i < summary.size(); i++) {
            TimeSheetEntryView entryView = widgetPresenter.composeTimeSheetEntryView(summary.getEntry(i));
            viewer.appendTimeSheetEntry(entryView);
        }
    }
}
