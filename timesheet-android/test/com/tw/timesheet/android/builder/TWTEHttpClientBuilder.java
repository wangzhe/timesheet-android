package com.tw.timesheet.android.builder;

import com.tw.timesheet.android.net.TWTEHttpClient;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class TWTEHttpClientBuilder {


    private HttpResponse response;

    public TWTEHttpClientStub build() {
        return new TWTEHttpClientStub();
    }

    public TWTEHttpClientBuilder response(HttpResponse response) {
        this.response = response;
        return this;
    }

    private class TWTEHttpClientStub extends TWTEHttpClient {

        private TWTEHttpClientStub() {
            super(new DefaultHttpClient());
        }

        @Override
        public HttpResponse execute(HttpRequest request) throws IOException {
            return response;
        }
    }
}
