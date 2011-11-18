package com.tw.timesheet.android.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

public class DataServer {

    public static final String SERVER_ADDRESS = "http://10.18.5.82:8000";

    private TWTEHttpClient httpClient;

    public DataServer(TWTEHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static DataServer createDataServer(TWTEHttpClient httpClient) {
        return new DataServer(httpClient);
    }

    public String getHttpRequest(HttpGet request) {
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (isSuccess(statusCode)) {
                return EntityUtils.toString(response.getEntity());
            }
            throw new Exception("error status code: " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String postHttpRequest(HttpPost request) {
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (isSuccess(statusCode)) {
                return EntityUtils.toString(response.getEntity());
            }
            throw new Exception("error status code: " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isSuccess(int statusCode) {
        return statusCode == 200 || statusCode == 201;
    }
}
