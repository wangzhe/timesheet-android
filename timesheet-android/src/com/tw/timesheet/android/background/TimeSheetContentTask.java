package com.tw.timesheet.android.background;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.TimeSheetService;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.net.TWTEHttpClient;
import com.tw.timesheet.android.system.DeviceSystem;
import org.apache.http.impl.client.DefaultHttpClient;

public class TimeSheetContentTask extends AsyncTask<Void, Void, TimeSheetSummary> {

    private DeviceSystem device;
    private StatusData statusData;
    private TimeSheetContentView viewer;

    public TimeSheetContentTask(TimeSheetContentView viewer, DeviceSystem device, StatusData statusData) {
        this.device = device;
        this.statusData = statusData;
        this.viewer = viewer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected TimeSheetSummary doInBackground(Void... params) {
        NetworkInfo networkInfo = device.getActiveNetworkInfo();
        DataServer dataServer = DataServer.createDataServer(new TWTEHttpClient(new DefaultHttpClient()));
        UserResource userResource = statusData.getUserResource();
        return new TimeSheetService().fetch(networkInfo, dataServer, userResource);
    }

    @Override
    protected void onPostExecute(TimeSheetSummary timeSheetSummary) {
        if(timeSheetSummary == null) return;
        viewer.appendTimeSheetSummary(timeSheetSummary);
    }

}
