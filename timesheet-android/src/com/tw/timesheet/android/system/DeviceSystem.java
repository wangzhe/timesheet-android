package com.tw.timesheet.android.system;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public interface DeviceSystem {

    NetworkInfo getActiveNetworkInfo();
}
