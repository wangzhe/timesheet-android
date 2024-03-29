package com.tw.timesheet.android.domain;

import android.net.NetworkInfo;
import com.tw.timesheet.android.net.DataServer;
import org.apache.http.client.methods.HttpGet;

public class TimeSheetService {

    public static final String SEARCH_REQUIRE_URI = "/searches";

    public TimeSheetService() {

    }

    public TimeSheetSummary fetch(NetworkInfo network, DataServer dataServer, UserResource userResource) {
        if (isOffline(network)) return null;
        String response;
        TimeSheetSummary timeSheetSummary;
//        HttpPost request = new TWTEHttpRequestComposer().createTimeSheetRequest(userResource.getURI(SEARCH_REQUIRE_URI));
        HttpGet request = new TWTEHttpRequestComposer().createTimeSheetRequestGet(userResource.getURI(SEARCH_REQUIRE_URI));
        response = dataServer.getHttpRequest(request);
        timeSheetSummary = new TimeSheetSummary.TimeSheetSummaryParser().parse(response);
        return timeSheetSummary;
    }

    private boolean isOffline(NetworkInfo network) {
        return network == null || !network.isConnectedOrConnecting();
    }

}
