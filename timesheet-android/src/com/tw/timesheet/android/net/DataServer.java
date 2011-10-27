package com.tw.timesheet.android.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

public class DataServer {

    private String responseContent;
    private TWTEHttpClient httpClient;

    public DataServer(TWTEHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static DataServer createDataServer(TWTEHttpClient httpClient) {
        return new DataServer(httpClient);
    }
    
    public String postHttpRequest(HttpPost request) {
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (isSuccess(statusCode)) {
                responseContent = EntityUtils.toString(response.getEntity());
                return responseContent;
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
