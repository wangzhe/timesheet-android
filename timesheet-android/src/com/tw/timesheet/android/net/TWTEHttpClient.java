package com.tw.timesheet.android.net;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class TWTEHttpClient {

    private DefaultHttpClient httpClient;

    public TWTEHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse execute(HttpRequest request) throws IOException {
        return httpClient.execute((HttpUriRequest) request);
    }
}
